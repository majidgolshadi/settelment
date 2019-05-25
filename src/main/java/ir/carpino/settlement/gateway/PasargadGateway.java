package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.pasargad.*;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPaya;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.CoreBatchTransferPayaResponse;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.GetTransferMoneyState;
import ir.carpino.settlement.entity.gateway.pasargad.userservices.GetTransferMoneyStateResponse;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.openssl.PEMReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PasargadGateway extends WebServiceGatewaySupport {

    @Autowired
    private PasargadGatewayConfiguration config;

    @Autowired
    private Jaxb2Marshaller marshaller;

    private final String DATE_FORMAT = "yyyy/MM/dd hh:mm:ss.SZ";  //2019/01/01 01:01:01:001
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final ObjectMapper mapper = new ObjectMapper();

    private List<PaymentInfo> paymentInfos = new ArrayList<>();

    private PaymentService observer;
    private MessageDigest md;
    private Cipher cipher;

    @PostConstruct
    void initPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
        Security.addProvider(new BouncyCastleProvider());

        // TODO: Must be replace with Bean but unfortunately Bean does not work currently
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);

        PrivateKey pvKey = ((KeyPair) new PEMReader(new FileReader(config.getPrivateKeyPath())).readObject()).getPrivate();
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvKey);

        md = MessageDigest.getInstance("SHA-1");
    }

    /**
     * CAASS stand for Carpino Automatic Accounting Settlement Service
     * @param driver driver information
     * @param balance rial
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

        if (paymentInfos.size() > config.getMaxTransactionPerBatch()) {
            return flushBatchSettleBuffer();
        }

        return true;
    }

    public String inquirySettle(SettlementState settleState) throws IOException, InterruptedException, BadPaddingException, IllegalBlockSizeException {
        GetTransferMoneyState request = entityToGetTransferMoneyStateConverter(new GetTransferMoneyStateInput(
                settleState.getUserId(), // ?
                settleState.getCreatedAt(),
                dateFormat.format(new Date()),
                settleState.getTransactionId()
        ));

        GetTransferMoneyStateResponse response = (GetTransferMoneyStateResponse) getWebServiceTemplate()
                .marshalSendAndReceive(config.getSoapUriWsdl(), request,
                        message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/GetTransferMoneyState"));

        String bankResponse = response.getGetTransferMoneyStateResult();
        GetTransactionResponseStruct obj = mapper.readValue(bankResponse, GetTransactionResponseStruct.class);

        if (!obj.IsSuccess) {
            log.error(bankResponse);
            return "";
        }

        log.info("bank response", bankResponse);

        return obj.Data.Key;
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
                config.getUsername(),
                stringTime,
                config.getSourceDeposit(),
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
            throws JsonProcessingException, BadPaddingException, IllegalBlockSizeException {
        String baseInputString = mapper.writeValueAsString(baseInput);

        String signedString = Base64.getEncoder().encodeToString(
                cipher.doFinal(md.digest(baseInputString.getBytes()))
        );

        CoreBatchTransferPaya req = new CoreBatchTransferPaya();
        req.setRequest(baseInputString);
        req.setSignature(signedString);

        return req;
    }

    private GetTransferMoneyState entityToGetTransferMoneyStateConverter(GetTransferMoneyStateInput baseInput)
            throws JsonProcessingException, InterruptedException, BadPaddingException, IllegalBlockSizeException {
        Thread.sleep(config.getDelayBetweenRequests());

        String baseInputString = mapper.writeValueAsString(baseInput);
        String signedString = Base64.getEncoder().encodeToString(
                cipher.doFinal(md.digest(baseInputString.getBytes()))
        );

        GetTransferMoneyState req = new GetTransferMoneyState();
        req.setRequest(baseInputString);
        req.setSignature(signedString);

        return req;
    }
}
