package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.SettlementStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
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

    public void settle(Driver driver, long balance) {
        settlementStateRepo.save(new SettlementState(driver.getId().toString(), balance));
        gateway.settle(driver, balance);
    }

    public void getPaymentResult(Map<String, String> paymentResult) {
        paymentResult.forEach(settlementStateRepo::setBankTransaction);
    }

    public void flushPaymentBuffer() {
        gateway.flushBatchSettleBuffer();
    }

}
