package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.SettlementStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class PaymentService {
    private final SettlementStateRepository settlementStateRepo;
    private final PasargadGateway gateway;
    private final SettlementConfiguration config;

    @Autowired
    public PaymentService(SettlementStateRepository settlementStateRepo, PasargadGateway pasargadGateway, SettlementConfiguration config) {
        this.settlementStateRepo = settlementStateRepo;
        this.gateway = pasargadGateway;
        this.config = config;
    }

    @PostConstruct
    public void initGatewayObserver() {
        gateway.setObserver(this);
    }

    public void settle(Optional<Driver> driver, long balance) {
        if (!driver.isPresent()) {
            log.error("unknown driver sent for settlement!");
            return;
        }

        Driver driverInfo = driver.get();

        if (driverInfo.getBankAccountInfo().getBankName().equals(config.getSkipSettleForBank())) {
            log.warn(String.format("driver %s with bank name %s skipped", driverInfo.getId(), driverInfo.getBankAccountInfo().bankName));
            return;
        }

        if (balance < config.getMinChargeToPay()) {
            log.warn(String.format("driver %s with balance %d is below balance limit", driverInfo.getId(), balance));
            return;
        }

        if (balance > config.getMaxChargeToPay()) {
            log.warn(String.format("[Fraud] driver %s with balance %d", driverInfo.getId(), balance));
            return;
        }

        settlementStateRepo.save(new SettlementState(driverInfo.getId(), balance));
        gateway.settle(driver.get(), balance);
    }

    /**
     * Store each settlement transaction id
     * @param paymentResult <userID, transactionId>
     */
    public void getPaymentResult(Map<String, String> paymentResult) {
        paymentResult.forEach(settlementStateRepo::setBankTransaction);
    }

    public void flushPaymentBuffer() {
        gateway.flushBatchSettleBuffer();
    }

    @Scheduled(cron = "${settlement.payment.inquiry-cron}")
    public void bankInquiry() {
        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateIsNull();
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);
                settlementStateRepo.setBankTransaction(settle.getUserId(), statusCode);
            } catch (IOException | InterruptedException | BadPaddingException | IllegalBlockSizeException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
