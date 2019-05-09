package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.passargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.passargad.PaymentInfo;
import ir.carpino.settlement.entity.gateway.passargad.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.rsa.crypto.RsaSecretEncryptor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.Serializable;
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
public class PasargadGateway {

    private final PasargadGatewayConfiguration config;
    private final PrivateKey pvKey;

    @Autowired
    public PasargadGateway(PasargadGatewayConfiguration config) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.config = config;

        byte[] bytes = Files.readAllBytes(Paths.get(config.getPrivateKeyPath()));
        PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        pvKey = kf.generatePrivate(ks);
    }

    public boolean CoreBatchTransferPayaRequest(List<PaymentInfo> userPaymentInfoList) {
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
            Request request = sendRequestGenerator(baseInput);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Request sendRequestGenerator(Serializable baseInput)
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

        return new Request(baseInputString, signdString);
    }
}
