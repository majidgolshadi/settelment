package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.gateway.pasargad.GetTransferMoneyStateInput;
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
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PaymentService {
    private final SettlementStateRepository settlementStateRepo;
    private final PasargadGateway gateway;

    @Autowired
    public PaymentService(SettlementStateRepository settlementStateRepo, PasargadGateway pasargadGateway) {
        this.settlementStateRepo = settlementStateRepo;
        this.gateway = pasargadGateway;
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

        settlementStateRepo.save(new SettlementState(driver.get().getId(), balance));
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
            } catch (IOException | InterruptedException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
