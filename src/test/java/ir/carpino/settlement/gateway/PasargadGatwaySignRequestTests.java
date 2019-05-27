package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.entity.gateway.pasargad.CoreBatchTransferPayaBaseInput;
import ir.carpino.settlement.entity.gateway.pasargad.GetTransferMoneyStateInput;
import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PasargadGatwaySignRequestTests {

    private final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS";  //2019/01/01 01:01:01:001
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final ObjectMapper mapper = new ObjectMapper();

    private MessageDigest md;
    private Cipher cipher;


    public void initPrivateKey() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        PrivateKey pvKey = ((KeyPair) new PEMReader(new FileReader("src/test/resources/test_private_rsa.pem")).readObject()).getPrivate();
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvKey);

        md = MessageDigest.getInstance("SHA-1");
    }

    @Test
    public void signCoreBatchTransferPayaBaseInputRequestTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, JsonProcessingException {
        Date date = new Date(1558908740);
        String username = "testUser";
        String sourceDeposit = "depositNumber1";

        List<PaymentInfo> paymentInfoList = new ArrayList<>();
        paymentInfoList.add(
                new PaymentInfo(
                1, "fullname",
                "description", "IRshebanumber123",
                "billnumber"
                )
        );

        CoreBatchTransferPayaBaseInput obj = new CoreBatchTransferPayaBaseInput(
                username, dateFormat.format(date),
                sourceDeposit, "ACH",
                paymentInfoList
        );

        String jsonData = mapper.writeValueAsString(obj);

        try {
            initPrivateKey();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


        PasargadGateway pasargadGateway = new PasargadGateway();
        ReflectionTestUtils.setField(pasargadGateway, "cipher", cipher);
        ReflectionTestUtils.setField(pasargadGateway, "md", md);

        Method method = PasargadGateway.class.getDeclaredMethod("signRequestContent", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(pasargadGateway, jsonData);

        Assert.assertEquals("J/Du2QyomdFEJGVrnEFDUdcyI94XEa/0dCnaieY/CV2y/IbQV3wBL4jqfW7KCAL9Lgu50BQKNPDecI5bGp+lvh2+7zQ5ne2VVCFiCTz6nmcvz4UDvCG6GlDC+nePD+1gT9moU9Xts1X6EbEy9p4nDnxVIssNvsGvDrwI7/mEg5i80r6SgMESGSWJM3nWzBu1tqvi60pPQUgfrmyNvzqJYbTEvKlYitz33CcVWRE19WhkdYu2I+QX+itiuCGNhAvUBjMPv3+5SkXot/oTjcXClY/d4sQGiAkISe5Qa/UqJ2REdwFocFn1KVolCl8Pw+msbYBl5KwII5LoepB3UYxHzw==", output);
    }

    @Test
    public void signGetTransferMoneyStateTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, JsonProcessingException {
        Date date = new Date(1558908740);
        String username = "testUser";
        String paymentId = "paymentId";

        GetTransferMoneyStateInput obj = new GetTransferMoneyStateInput(
                username, dateFormat.format(date), dateFormat.format(date), paymentId
        );

        String jsonData = mapper.writeValueAsString(obj);

        try {
            initPrivateKey();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


        PasargadGateway pasargadGateway = new PasargadGateway();
        ReflectionTestUtils.setField(pasargadGateway, "cipher", cipher);
        ReflectionTestUtils.setField(pasargadGateway, "md", md);

        Method method = PasargadGateway.class.getDeclaredMethod("signRequestContent", String.class);
        method.setAccessible(true);
        String output = (String) method.invoke(pasargadGateway, jsonData);

        Assert.assertEquals("mj+e3/8ytF2SgU4KxUhfj8+Hh6m6dHX/RrCW6684GF+WSGJDvJvvaKaaV6NnVkD5V4VFFGvgc4mV6ekvwUHEgno4mnS0r0+dKSumxlXrXQt8PHVfVYd9LvZ6JMg0sTeuNP+JL9gULJpTlhXnxIbHNRD3M12ssQXtGouiM1OZ6+jgTc6YN5F2k2mNG/VU+5yNbw6SmMDnkT+WPIuYMwERWy60nxQYodbWkcUQJT78Ak/aetPHohgkfY90g9mKePMO+BeWgBHu7gNPrpDmviqx3DYUUYhnq/yMpMqlQLxFkAxA/AnE4bD7kV/BsQ2ubSBcqrQYg9WeOsGL/44CYmrkLA==", output);
    }
}
