package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.passargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.passargad.PaymentInfo;
import ir.co.fanap.toranj.ibank.userservices.CoreBatchTransferPaya;
import ir.co.fanap.toranj.ibank.userservices.CoreBatchTransferPayaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class PasargadGateway extends WebServiceGatewaySupport {

    @Autowired
    private PasargadGatewayConfiguration config;

    private PrivateKey pvKey;


    @PostConstruct
    public void initPvKey(PasargadGatewayConfiguration config) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Files.readAllBytes(Paths.get(config.getPrivateKeyPath()));
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        pvKey = kf.generatePrivate(ks);
    }

    public CoreBatchTransferPayaResponse CoreBatchTransferPayaRequest(List<PaymentInfo> userPaymentInfoList) {
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
            CoreBatchTransferPaya request = entityToCoreBatchTransferPayaConverter(baseInput);

            return (CoreBatchTransferPayaResponse) getWebServiceTemplate()
                    .marshalSendAndReceive("https://ib.bpi.ir/WebServices/UserServices.asmx?wsdl", request,
                            message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/CoreBatchTransferPaya"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
}
