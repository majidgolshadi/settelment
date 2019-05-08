package ir.carpino.settlement.gateway;

import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.Driver;
import ir.carpino.settlement.entity.gateway.passargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.passargad.PaymentInfo;
import ir.carpino.settlement.entity.gateway.passargad.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PasargadGateway {

    @Autowired
    PasargadGatewayConfiguration configuration;

    public boolean CoreBatchTransferPayaRequest(List<PaymentInfo> userPaymentInfoList) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SZ"); //2019/01/01 01:01:01:001
        Date date = new Date();
        String stringTime = dateFormat.format(date).toString();

        CoreBatchTransferPayaBaseInput baseInput = new CoreBatchTransferPayaBaseInput(
                configuration.username,
                stringTime,
                configuration.sourceDeposit,
                String.format("ACH_carpino_%s", stringTime),
                userPaymentInfoList
        );

        Request request = sendRequestGenerator(baseInput);

        return false;
    }

    private Request sendRequestGenerator(Serializable request) {

        return new Request("request", "sig");
    }
}
