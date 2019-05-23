package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.pasargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.pasargad.CoreBatchTransferPayaResponseStruct;
import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPaya;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPayaResponse;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PasargadGateway extends WebServiceGatewaySupport {

    @Autowired
    private PasargadGatewayConfiguration config;

    private final String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss.SZ";  //2019/01/01 01:01:01:001
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final ObjectMapper mapper = new ObjectMapper();

    private List<PaymentInfo> paymentInfos = new ArrayList<>();

    private PrivateKey pvKey;
    private PaymentService observer;

    @PostConstruct
    void initPrivateKey(PasargadGatewayConfiguration config) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Files.readAllBytes(Paths.get(config.getPrivateKeyPath()));

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        pvKey = kf.generatePrivate(ks);
    }

    /**
     * CAASS stand for Carpino Automatic Accounting Settlement Service
     * @param driver
     * @param balance
     * @return
     */
    public boolean settle(Driver driver, long balance) {
        String stringTime = dateFormat.format(new Date());
        String fullName = String.format("%s %s", driver.getFirstName(), driver.getLastName());

        paymentInfos.add(new PaymentInfo(
            balance,
            fullName,
            String.format("carpino settlement with %s till %s", fullName, stringTime),
            driver.getBankAccountInfo().getShabaNumber(),
            String.format("CAASS-%d-%s", paymentInfos.size(), driver.getId())
        ));

        if (paymentInfos.size() > config.maxTransactionPerBatch) {
            return flushBatchSettleBuffer();
        }

        return true;
    }

    public boolean flushBatchSettleBuffer() {
        if (paymentInfos.size() < 1) {
            return false;
        }

        try {
            coreBatchTransferPayaAction(paymentInfos);

        } catch (InstantiationException | IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }

        paymentInfos.clear();
        log.debug("flush batch payment list");

        return true;
    }

    public void setObserver(PaymentService paymentService) {
        observer = paymentService;
    }

    private void notifyBatchSettleObserver(String bankResponse) throws IOException {
        CoreBatchTransferPayaResponseStruct obj = mapper.readValue(bankResponse, CoreBatchTransferPayaResponseStruct.class);

        if (!obj.IsSuccess) {
            log.error(bankResponse);
            return;
        }

        log.info("bank response", bankResponse);
        if (observer == null) {
            return;
        }

        observer.getPaymentResult(obj.Data
                .stream()
                .collect(Collectors.toMap(data -> data.BillNumber.split("-")[2], data -> data.BillNumber)));
    }

    private void coreBatchTransferPayaAction(List<PaymentInfo> userPaymentInfoList) throws InstantiationException, IOException {
        CoreBatchTransferPaya request;
        String stringTime = dateFormat.format(new Date());

        CoreBatchTransferPayaBaseInput baseInput = new CoreBatchTransferPayaBaseInput(
                config.username,
                stringTime,
                config.sourceDeposit,
                String.format("ACH_carpino_%s", stringTime),
                userPaymentInfoList
        );

        try {
             request = entityToCoreBatchTransferPayaConverter(baseInput);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InstantiationException(e.getMessage());
        }

        CoreBatchTransferPayaResponse response = (CoreBatchTransferPayaResponse) getWebServiceTemplate()
                .marshalSendAndReceive(config.getSoapUriWsdl(), request,
                        message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/CoreBatchTransferPaya"));

        notifyBatchSettleObserver(response.getCoreBatchTransferPayaResult());
    }

    private CoreBatchTransferPaya entityToCoreBatchTransferPayaConverter(CoreBatchTransferPayaBaseInput baseInput)
            throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            BadPaddingException,
            IllegalBlockSizeException,
            JsonProcessingException
    {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvKey);

        String baseInputString = mapper.writeValueAsString(baseInput);


        String signedString = Base64.getEncoder().encodeToString(
                cipher.doFinal(baseInputString.getBytes())
        );

        CoreBatchTransferPaya req = new CoreBatchTransferPaya();
        req.setRequest(baseInputString);
        req.setSignature(signedString);

        return req;
    }
}
