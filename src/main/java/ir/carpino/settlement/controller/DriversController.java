package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.BankAccountInfo;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DriversController {

    private final WalletService walletService;
    private final DriversRepository driversRepo;
    private final RidesRepository rideRepo;
    private final PaymentService paymentService;
    private final PasargadGateway gateway;

    @Autowired
    public DriversController(RidesRepository rideRepo, PaymentService paymentService, WalletService walletService, DriversRepository driversRepo, PasargadGateway gateway) {
        this.driversRepo = driversRepo;
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.paymentService = paymentService;

        this.gateway = gateway;
    }

    @PostMapping("/v1/settlement/driver/active-from/{time}")
    public void activeDriversSettlement(@PathVariable("time") int time) {
        rideRepo.findRidesByStatusEqualsAndRideInfoRealStartRideDateGreaterThan("COMPLETED", new Date(time))
                .stream()
                .map(Ride::getDriver)
                .collect(Collectors.toMap(driversRepo::findById, walletService::getUserBalance))
                .forEach(paymentService::settle);

        paymentService.flushPaymentBuffer();
    }

    @GetMapping("/v1/test")
    public void test() {
        gateway.settle(new Driver("1", "حمیدرضا", "ظفری",
                new BankAccountInfo("sag", "IR450570029880010690634101")), 200000);

        gateway.flushBatchSettleBuffer();
    }

    @GetMapping("/v1/test2")
    public void test2() {
        try {
            gateway.inquirySettle(new SettlementState("1", "9803050572048643", 1000));
        } catch (UnsuccessfulRequestException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
