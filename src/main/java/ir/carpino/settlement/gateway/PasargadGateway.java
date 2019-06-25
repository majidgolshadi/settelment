package ir.carpino.settlement.gateway;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
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

    private final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS";  //2019/01/01 01:01:01:001
    private final String AHC_DATE_FORMAT = "yyyyMMddHHmmssSSS";  //20190101010101001
    private final String INQ_DATE_FORMAT = "yyyy/MM/dd";  //2019/01/01
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final DateFormat ahcDateFormat = new SimpleDateFormat(AHC_DATE_FORMAT);
    private final DateFormat inqDateFormat = new SimpleDateFormat(INQ_DATE_FORMAT);
    private final ObjectMapper mapper = new ObjectMapper();

    private List<PaymentInfo> paymentInfos = new ArrayList<>();

    private PaymentService observer;
    private MessageDigest md;
    private Cipher cipher;

    @PostConstruct
    void initPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
        Security.addProvider(new BouncyCastleProvider());

        // TODO: Must be replace with Bean but unfortunately Bean does not work in java 11
        this.setMarshaller(marshaller);
        this.setUnmarshaller(marshaller);

        PrivateKey pvKey = ((KeyPair) new PEMReader(new FileReader(config.getPrivateKeyPath())).readObject()).getPrivate();
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pvKey);

        md = MessageDigest.getInstance("SHA-1");
    }

    public void setObserver(PaymentService paymentService) {
        observer = paymentService;
    }

    /**
     * CarpinoAASS stand for Carpino Automatic Accounting Settlement Service
     * @param driver driver information
     * @param balance rial
     * @return
     */
    public boolean settle(Driver driver, String billingNumber, long balance) {
        String stringTime = dateFormat.format(new Date());
        String fullName = String.format("%s %s", driver.getFirstName(), driver.getLastName());

        paymentInfos.add(new PaymentInfo(
            balance,
            fullName,
            String.format("تصفیه حساب کارپینو با %s تا تاریخ %s", fullName, stringTime),
            driver.getBankAccountInfo().getShabaNumber(),
            billingNumber
        ));

        if (paymentInfos.size() >= config.getMaxTransactionPerBatch()) {
            return flushBatchSettleBuffer();
        }

        return true;
    }

    public boolean flushBatchSettleBuffer() {
        if (paymentInfos.size() < 1) {
            return false;
        }

        try {
            List<CoreBatchTransferPayaResponseData> responseData = payaBatchTransfer(paymentInfos);
            notifyBatchSettleObserver(responseData);

        } catch (InstantiationException | IOException | UnsuccessfulRequestException e) {
            log.error(e.getMessage());
            return false;
        }

        paymentInfos.clear();
        log.debug("flush batch payment list");

        return true;
    }

    private void notifyBatchSettleObserver(List<CoreBatchTransferPayaResponseData> dataList){
        if (observer == null)
            return;

        Map<String, String> report = dataList.stream()
                .collect(Collectors.toMap(data -> data.BillNumber.split("USR")[1], data -> data.BillNumber));

        observer.getPaymentResult(report);
    }

    private List<CoreBatchTransferPayaResponseData> payaBatchTransfer(List<PaymentInfo> userPaymentInfoList) throws InstantiationException, IOException, UnsuccessfulRequestException {
        CoreBatchTransferPaya request;
        Date date = new Date();
        String stringTime = dateFormat.format(date);

        CoreBatchTransferPayaBaseInput baseInput = new CoreBatchTransferPayaBaseInput(
                config.getUsername(),
                stringTime,
                config.getSourceDeposit(),
                String.format("ACHcarpinoTime%s", ahcDateFormat.format(date)),
                userPaymentInfoList
        );

        try {
            request = new CoreBatchTransferPaya();

            String jsonData = mapper.writeValueAsString(baseInput);
            request.setRequest(jsonData);
            request.setSignature(signRequestContent(jsonData));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InstantiationException(e.getMessage());
        }

        return soapActionCoreBatchTransferPaya(request);
    }

    public String inquirySettle(SettlementState settleState)
            throws UnsuccessfulRequestException, InstantiationException, IOException
    {
        GetTransferMoneyState request = new GetTransferMoneyState();

        GetTransferMoneyStateInput getTransferMoneyStateInput = new GetTransferMoneyStateInput(
                config.getUsername(),
                inqDateFormat.format(settleState.getCreatedAt()),
                dateFormat.format(new Date()),
                settleState.getPaymentId()
        );

        try {
            String jsonData = mapper.writeValueAsString(getTransferMoneyStateInput);
            log.info(jsonData);
            request.setRequest(jsonData);
            request.setSignature(signRequestContent(jsonData));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InstantiationException(e.getMessage());
        }

        GetTransactionMoneyStateResponseData data = soapActionGetTransferMoneyState(request);
        String statusCode = data.Key;

        if (statusCode.equals("sent_recieved") || statusCode.equals("registered") || statusCode.equals("confirmed")) {
            return "received";
        }

        return data.Key;
    }

    private List<CoreBatchTransferPayaResponseData> soapActionCoreBatchTransferPaya(CoreBatchTransferPaya request)
            throws IOException, UnsuccessfulRequestException
    {
        CoreBatchTransferPayaResponse response = (CoreBatchTransferPayaResponse) getWebServiceTemplate()
                .marshalSendAndReceive(config.getSoapUriWsdl(), request,
                        message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/CoreBatchTransferPaya"));

        String bankResponse = response.getCoreBatchTransferPayaResult();
        log.debug("response: ", bankResponse);

        JavaType type = mapper.getTypeFactory()
                .constructParametricType(ApiResponseList.class, CoreBatchTransferPayaResponseData.class);
        ApiResponseList<CoreBatchTransferPayaResponseData> obj = mapper.readValue(bankResponse, type);

        if (!obj.IsSuccess) {
            log.error(bankResponse);
            throw new UnsuccessfulRequestException(obj.Message);
        }

        return obj.Data;
    }

    private GetTransactionMoneyStateResponseData soapActionGetTransferMoneyState(GetTransferMoneyState request)
            throws IOException, UnsuccessfulRequestException
    {
        GetTransferMoneyStateResponse response = (GetTransferMoneyStateResponse) getWebServiceTemplate()
                .marshalSendAndReceive(config.getSoapUriWsdl(), request,
                        message -> ((SoapMessage)message).setSoapAction("http://ibank.toranj.fanap.co.ir/UserServices/GetTransferMoneyState"));

        String bankResponse = response.getGetTransferMoneyStateResult();
        log.debug("response: ", bankResponse);

        JavaType type = mapper.getTypeFactory()
                .constructParametricType(ApiResponse.class, GetTransactionMoneyStateResponseData.class);
        ApiResponse<GetTransactionMoneyStateResponseData> obj = mapper.readValue(bankResponse, type);

        if (!obj.IsSuccess) {
            log.error(bankResponse);
            throw new UnsuccessfulRequestException(obj.Message);
        }

        return obj.Data;
    }

//    private <T> T fetchData(String response, ApiResponse<T> obj) throws UnsuccessfulRequestException {
//        if (!obj.IsSuccess) {
//            log.error(response);
//            throw new UnsuccessfulRequestException(obj.Message);
//        }
//
//        log.info("bank response", response);
//        return obj.Data;
//    }

    private String signRequestContent(String jsonRequest) throws BadPaddingException, IllegalBlockSizeException {
        byte[] sha1 = md.digest(jsonRequest.getBytes());
        byte[] signWithRsa = cipher.doFinal(sha1);

        return Base64.getEncoder().encodeToString(signWithRsa);
    }
}
