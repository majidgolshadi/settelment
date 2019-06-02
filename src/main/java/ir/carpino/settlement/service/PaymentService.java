package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.EntityTransaction;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import ir.carpino.settlement.repository.SettlementStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PaymentService {
    private final SettlementStateRepository settlementStateRepo;
    private final EntryTransactionRepository entryTransactionRepo;
    private final PasargadGateway gateway;
    private final SettlementConfiguration config;

    @Autowired
    public PaymentService(SettlementStateRepository settlementStateRepo, EntryTransactionRepository entryTransactionRepo,
                          PasargadGateway pasargadGateway, SettlementConfiguration config) {
        this.settlementStateRepo = settlementStateRepo;
        this.entryTransactionRepo = entryTransactionRepo;
        this.gateway = pasargadGateway;
        this.config = config;
    }

    @PostConstruct
    public void initGatewayObserver() {
        gateway.setObserver(this);
    }

    public void settle(Driver driver, long balance) {
        if (driver.getBankAccountInfo() == null) {
            log.warn(String.format("driver %s bank info is empty", driver.getId()));
            return;
        }

        if (driver.getBankAccountInfo().getBankName().equals(config.getSkipSettleForBank())) {
            log.warn(String.format("driver %s with bank name %s skipped", driver.getId(), driver.getBankAccountInfo().getBankName()));
            return;
        }

        if (balance < config.getMinChargeToPay()) {
            log.warn(String.format("driver %s with balance %d is below balance limit", driver.getId(), balance));
            return;
        }

        if (balance > config.getMaxChargeToPay()) {
            log.warn(String.format("[Fraud] driver %s with balance %d", driver.getId(), balance));
            return;
        }

        log.info(String.format("settle %d for driver %s", balance, driver.getId()));
        settlementStateRepo.save(new SettlementState(driver.getId(), balance, driver.getBankAccountInfo().getShabaNumber()));
        gateway.settle(driver, balance);
    }

    /**
     * Store each settlement transaction id
     * @param paymentResult <userID, transactionId>
     */
    public void getPaymentResult(Map<String, String> paymentResult) {
        paymentResult.forEach(settlementStateRepo::setBankPaymentId);
    }

    public void flushPaymentBuffer() {
        gateway.flushBatchSettleBuffer();
    }

    @Scheduled(cron = "${settlement.payment.inquiry-cron}")
    public void bankInquiry() {
        log.info("cron job fired");
        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateIsNull();
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);
                Date date = new Date();

                settle.setBankState(statusCode);
                settle.setUpdatedAt(date);
                settlementStateRepo.save(settle);

                if (statusCode.equals("received")) {
                    EntityTransaction et = new EntityTransaction();
                    et.setType("DRIVER_SETTLE");
                    et.setFromUserId("MASTER_OUTCOME_ID0000000");
                    et.setFromUserRole("MASTER_OUTCOME");
                    et.setModifiedDate(date.getTime());
                    et.setUserId(settle.getUserId());
                    et.setUserRole("DRIVER");
                    et.setWithdraw(settle.getBalance());
                    et.setEntryTransactionId(settle.getPaymentId());
                    et.setShabaNumber(settle.getShabaNumber().replace("IR", ""));
                    entryTransactionRepo.save(et);

                    // update mongo balance
                }

            } catch (IOException | UnsuccessfulRequestException | InstantiationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
