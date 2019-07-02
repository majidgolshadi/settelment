package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.entity.mysql.SettlementStateBankState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.SettlementStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class PaymentService {
    private final SettlementStateRepository settlementStateRepo;
    private final WalletService walletService;
    private final PasargadGateway gateway;
    private final SettlementConfiguration config;
    private final DateFormat dateFormat;

    private final Random random;

    private final String BILLING_DATE_FORMAT = "yyyyMMddHHmmssSSS";  //20190101010101001
    private final int min = 1_000;
    private final int max = 9_999;


    @Autowired
    public PaymentService(WalletService walletService, PasargadGateway pasargadGateway,
                          SettlementStateRepository settlementStateRepo,
                          SettlementConfiguration config
    ) {
        this.walletService = walletService;
        this.gateway = pasargadGateway;
        this.settlementStateRepo = settlementStateRepo;
        this.config = config;

        random = new Random();
        dateFormat = new SimpleDateFormat(BILLING_DATE_FORMAT);
    }

    @PostConstruct
    public void initGatewayObserver() {
        gateway.setObserver(this);
    }

    private boolean passFilter(Driver driver, long balance) {
        if (driver.getBankAccountInfo() == null) {
            log.warn(String.format("driver %s bank info is empty", driver.getId()));
            return false;
        }

        // https://bmi.ir/fa/pages/192/%D9%85%D8%B4%D8%AE%D8%B5%D8%A7%D8%AA%20%D9%85%D9%84%DB%8C%20%D8%B4%D9%86%D8%A7%D8%B3%D9%87%20%D8%AD%D8%B3%D8%A7%D8%A8%20%D8%A8%D8%A7%D9%86%DA%A9%DB%8C%20%D8%A7%DB%8C%D8%B1%D8%A7%D9%86%20(%D8%B4%D8%A8%D8%A7)
        if (driver.getBankAccountInfo().getShabaNumber().length() < 24) {
            log.warn(String.format("driver %s invalid shaba number", driver.getId()));
            return false;
        }

        if (driver.getBankAccountInfo().getBankName().equals(config.getSkipSettleForBank())) {
            log.warn(String.format("driver %s with bank name %s skipped", driver.getId(), driver.getBankAccountInfo().getBankName()));
            return false;
        }

        if (balance < config.getMinChargeToPay()) {
            log.warn(String.format("driver %s with balance %d is below balance limit", driver.getId(), balance));
            return false;
        }

        if (balance > config.getMaxChargeToPay()) {
            log.warn(String.format("[Fraud] driver %s with balance %d", driver.getId(), balance));
            return false;
        }

        return true;
    }

    public void settle(Driver driver, long balance) {
        if (!passFilter(driver, balance)) {
            return;
        }

        String paymentId = String.format("Carpino%sRC%s", dateFormat.format(new Date()), random.nextInt((max - min) + 1) + min);

        log.info(String.format("settle %d for driver %s with payment id %s", balance, driver.getId(), paymentId));

        if (config.isTestMode()) {
            log.warn("payment skipped; app is in test mode");
            return;
        }

        settlementStateRepo.save(new SettlementState(paymentId, driver.getId(), balance));
        gateway.settle(driver, paymentId, balance);
        walletService.decreaseDriverWalletBalance(driver, balance);
    }

    /**
     * Notify from gateway to update settle state
     * @param paymentId
     * @param successful
     */
    public void updateSettleBankState(String paymentId, boolean successful) {
        Optional<SettlementState> stateOpt = settlementStateRepo.findById(paymentId);

        if (!stateOpt.isPresent()) {
            log.error("paymentId {} does not exist", paymentId);
            return;
        }

        SettlementState state = stateOpt.get();

        if (successful) {
            state.setBankState(SettlementStateBankState.REQUEST_SENT);
        } else {
            state.setBankState(SettlementStateBankState.FAILED_REVERT);
            walletService.revertDriverWalletBalance(state.getUserId(), state.getBalance());
        }

        settlementStateRepo.save(state);
    }

    public void flushPaymentBuffer() {
        gateway.flushBatchSettleBuffer();
    }

    @Scheduled(cron = "${settlement.payment.inquiry-cron}")
    public void bankInquiry() {
        log.info("cron job fired");

        if (config.isTestMode()) {
            log.warn("recheck skipped; app is in test mode");
            return;
        }

        if (!config.isRecheckSettle()) {
            log.warn("recheck skipped; recheck settle false");
            return;
        }

        List<SettlementState> failedSettlements = settlementStateRepo.findAllByBankStateIsNull();
        failedSettlements.stream().forEach(settle -> {
            walletService.revertDriverWalletBalance(settle.getUserId(), settle.getBalance());
        });


        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateEquals(SettlementStateBankState.REQUEST_SENT);
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);

                settle.setBankState(statusCode);
                settlementStateRepo.save(settle);

                if (!statusCode.equals(SettlementStateBankState.RECEIVED)) {
                    walletService.revertDriverWalletBalance(settle.getUserId(), settle.getBalance());
                }

            } catch (IOException | UnsuccessfulRequestException | InstantiationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
