package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.gateway.PasargadGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PasargadGateway pasargadGateway;

    private List<PaymentInfo> paymentInfoList = new ArrayList<>();

    public void settle(PaymentInfo paymentInfo) {
        if (paymentInfoList.size() < pasargadGateway.getMaxBatchListSize()) {
            paymentInfoList.add(paymentInfo);
        }

        flushSettlementBuffer();
    }

    public void flushSettlementBuffer() {
        try {
            pasargadGateway.batchPayment(paymentInfoList);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        paymentInfoList.clear();
    }

}
