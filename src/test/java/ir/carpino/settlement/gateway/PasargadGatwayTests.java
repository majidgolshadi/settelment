package ir.carpino.settlement.gateway;

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

public class PasargadGatwayTests {

    private final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS";  //2019/01/01 01:01:01:001
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

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
    public void signCoreBatchTransferPayaBaseInputRequestTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
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

        try {
            initPrivateKey();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


        PasargadGateway pasargadGateway = new PasargadGateway();
        ReflectionTestUtils.setField(pasargadGateway, "cipher", cipher);
        ReflectionTestUtils.setField(pasargadGateway, "md", md);

        Method method = PasargadGateway.class.getDeclaredMethod("signRequestContent", Object.class);
        method.setAccessible(true);
        String output = (String) method.invoke(pasargadGateway, obj);

        Assert.assertEquals("J/Du2QyomdFEJGVrnEFDUdcyI94XEa/0dCnaieY/CV2y/IbQV3wBL4jqfW7KCAL9Lgu50BQKNPDecI5bGp+lvh2+7zQ5ne2VVCFiCTz6nmcvz4UDvCG6GlDC+nePD+1gT9moU9Xts1X6EbEy9p4nDnxVIssNvsGvDrwI7/mEg5i80r6SgMESGSWJM3nWzBu1tqvi60pPQUgfrmyNvzqJYbTEvKlYitz33CcVWRE19WhkdYu2I+QX+itiuCGNhAvUBjMPv3+5SkXot/oTjcXClY/d4sQGiAkISe5Qa/UqJ2REdwFocFn1KVolCl8Pw+msbYBl5KwII5LoepB3UYxHzw==", output);
    }

    @Test
    public void signGetTransferMoneyStateTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Date date = new Date(1558908740);
        String username = "testUser";
        String paymentId = "paymentId";

        GetTransferMoneyStateInput obj = new GetTransferMoneyStateInput(
                username, date, dateFormat.format(date), paymentId
        );

        try {
            initPrivateKey();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


        PasargadGateway pasargadGateway = new PasargadGateway();
        ReflectionTestUtils.setField(pasargadGateway, "cipher", cipher);
        ReflectionTestUtils.setField(pasargadGateway, "md", md);

        Method method = PasargadGateway.class.getDeclaredMethod("signRequestContent", Object.class);
        method.setAccessible(true);
        String output = (String) method.invoke(pasargadGateway, obj);

        Assert.assertEquals("d/3smyJeefv2Eud7vX0c65ZIh01nwGLsmUVloky+785K43U8MzUh6hsE1OFQsYpnN7QaXF32Mnpe2ItmGNTcRVySK9bFbwWTY1XsiVqGP+GyyOhKJvux1L+PM6zk1Qjj9omIIyJY1/mcvPOZr71hQzbN2s8dyOrBqlrTnjmtK7ivy49RH87MxboXvDOWpJ0k26OX8paReLtCJ8CraLrDJtg3JyW556iWmSCa+h//3Zp9CzydFY9S14ZKljUfq4T3u3ayLjXkrl6o+Blvk4ThDwcVWoPNUo5j+v2dNh10llyIebMy+39/GM2MTha4UQIwStL1anaYU3y+XXtriOvl3w==", output);
    }
}
