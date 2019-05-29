package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.gateway.PasargadGateway;
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

    public void settle(Driver driver, long balance) {
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

        settlementStateRepo.save(new SettlementState(driver.getId(), balance));
//        gateway.settle(driver, balance);
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
        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateIsNull();
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);

                settle.setBankState(statusCode);
                settle.setUpdatedAt(new Date());
                settlementStateRepo.save(settle);

            } catch (IOException | UnsuccessfulRequestException | InstantiationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
