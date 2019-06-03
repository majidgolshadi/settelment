package ir.carpino.settlement.gateway;

import ir.carpino.settlement.configuration.PasargadGatewayConfiguration;
import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.entity.mongo.BankAccountInfo;
import ir.carpino.settlement.entity.mongo.Driver;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.when;

public class PasargadGatewayTests {

    @Test
    public void initPrivateKeyTest() {

    }

    @Test
    public void settleMaxTransactionBerBatchLimitTest() {
        PasargadGateway gateway = new PasargadGateway();
        PasargadGateway gatewaySpy = Mockito.spy(gateway);

        PasargadGatewayConfiguration config = new PasargadGatewayConfiguration();
        config.setMaxTransactionPerBatch(100);

        ReflectionTestUtils.setField(gateway, "config", config);
        when(gatewaySpy.flushBatchSettleBuffer()).thenReturn(false);

        BankAccountInfo bankAccountInfo = new BankAccountInfo("bank", "IR01");
        Driver driver = new Driver("1", "name", "lastname", bankAccountInfo, 0D);

        boolean result = gateway.settle(driver, "bid", 1000);

        Assert.assertEquals(true, result);
    }

    @Test
    public void settleCallFlushBatchSettleBufferTest() {
        PasargadGateway gateway = new PasargadGateway();
        PasargadGateway gatewaySpy = Mockito.spy(gateway);

        PasargadGatewayConfiguration config = new PasargadGatewayConfiguration();
        config.setMaxTransactionPerBatch(1);

        ReflectionTestUtils.setField(gateway, "config", config);
        when(gatewaySpy.flushBatchSettleBuffer()).thenReturn(false);

        BankAccountInfo bankAccountInfo = new BankAccountInfo("bank", "IR01");
        Driver driver = new Driver("1", "name", "lastname", bankAccountInfo, 0D);

        boolean result = gateway.settle(driver, "bid", 1000);

        Assert.assertFalse(result);
    }

    @Test
    public void settleAppendPaymentInfoTest() {
        PasargadGateway gateway = new PasargadGateway();
        PasargadGateway gatewaySpy = Mockito.spy(gateway);

        PasargadGatewayConfiguration config = new PasargadGatewayConfiguration();
        config.setMaxTransactionPerBatch(100);

        ReflectionTestUtils.setField(gateway, "config", config);
        when(gatewaySpy.flushBatchSettleBuffer()).thenReturn(false);

        List<PaymentInfo> paymentInfoList = new ArrayList<>();
        ReflectionTestUtils.setField(gateway, "paymentInfos", paymentInfoList);


        int balance = 1000;
        BankAccountInfo bankAccountInfo = new BankAccountInfo("bank", "IR01");
        Driver driver = new Driver("1", "firstName", "lastName", bankAccountInfo, 0D);

        gateway.settle(driver, "bid", balance);

        Assert.assertEquals(1, paymentInfoList.size());
        Assert.assertEquals(balance, paymentInfoList.get(0).getAmount());
        Assert.assertTrue(paymentInfoList.get(0).getBeneficiaryFullName().length() > 2);
        Assert.assertEquals(driver.getBankAccountInfo().getShabaNumber(), paymentInfoList.get(0).getDestShebaNumber());
        Assert.assertTrue(paymentInfoList.get(0).getBillNumber().length() > 16);
    }

    @Test
    public void emptyFlushBatchSettleBufferTest() {
        PasargadGateway gateway = new PasargadGateway();
        Assert.assertFalse(gateway.flushBatchSettleBuffer());
    }

    @Test
    public void flushFullBatchSettleBufferTest() {
        PasargadGateway gateway = new PasargadGateway();
        PasargadGateway gatewaySpy = Mockito.spy(gateway);

        PasargadGatewayConfiguration config = new PasargadGatewayConfiguration();
        config.setMaxTransactionPerBatch(100);

        ReflectionTestUtils.setField(gateway, "config", config);
        when(gatewaySpy.flushBatchSettleBuffer()).thenReturn(false);

        List<PaymentInfo> paymentInfoList = new ArrayList<>();
        ReflectionTestUtils.setField(gateway, "paymentInfos", paymentInfoList);


        int balance = 1000;
        BankAccountInfo bankAccountInfo = new BankAccountInfo("bank", "IR01");
        Driver driver = new Driver("1", "firstName", "lastName", bankAccountInfo, 0D);

        gateway.settle(driver, "bid", balance);

//        when(gatewaySpy.payaBatchTransfer(paymentInfoList)).thenThrow(new IOException());
//
//        Assert.assertEquals(0, paymentInfoList.size());
    }

    @Test
    public void notifyBatchSettleObserverTest() {

    }

    @Test
    public void payaBatchTransferTest() {

    }

    @Test
    public void inquirySettleTest() {
    }

    @Test
    public void soapActionCoreBatchTransferPayaTest() {

    }

    @Test
    public void soapActionGetTransferMoneyStateTest() {

    }
}
