package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.pasargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPaya;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPayaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

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
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class PasargadGateway extends WebServiceGatewaySupport {


    private final PasargadGatewayConfiguration config;
    private PrivateKey pvKey;

    @Autowired
    public PasargadGateway(PasargadGatewayConfiguration config, Jaxb2Marshaller marshaller) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.config = config;

        this.setDefaultUri("https://ib.bpi.ir/WebServices/UserServices.asmx");
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);

        initPrivateKey(config);
    }

    public int getMaxBatchListSize() {
        return config.maxTransactionPerBatch;
    }

    public boolean batchPayment(List<PaymentInfo> userPaymentInfoList) throws InstantiationException {
        CoreBatchTransferPayaResponse response = coreBatchTransferPayaAction(userPaymentInfoList);
        String json = response.getCoreBatchTransferPayaResult();

        log.info(json);

        return true;
    }

    private CoreBatchTransferPayaResponse coreBatchTransferPayaAction(List<PaymentInfo> userPaymentInfoList) throws InstantiationException {
        CoreBatchTransferPaya request;

        if (userPaymentInfoList.size() > getMaxBatchListSize()) {
            throw new RuntimeException(String.format("The list size must less than %d", getMaxBatchListSize()));
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SZ"); //2019/01/01 01:01:01:001
        Date date = new Date();
        String stringTime = dateFormat.format(date);

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
            throw new InstantiationException(e.getMessage());
        }

        return (CoreBatchTransferPayaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("https://ib.bpi.ir/WebServices/UserServices.asmx?wsdl", request,
                        message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/CoreBatchTransferPaya"));
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
        ObjectMapper mapper = new ObjectMapper();

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvKey);

        String baseInputString = mapper.writeValueAsString(baseInput);


        String signdString = Base64.getEncoder().encodeToString(
                cipher.doFinal(baseInputString.getBytes())
        );

        CoreBatchTransferPaya req = new CoreBatchTransferPaya();
        req.setRequest(baseInputString);
        req.setSignature(signdString);

        return req;
    }

    private void initPrivateKey(PasargadGatewayConfiguration config) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Files.readAllBytes(Paths.get(config.getPrivateKeyPath()));

        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        pvKey = kf.generatePrivate(ks);
    }
}
