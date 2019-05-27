package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class DriversController {

    private final WalletService walletService;
    private final DriversRepository driversRepo;
    private final RidesRepository rideRepo;
    private final PaymentService paymentService;

    @Autowired
    public DriversController(RidesRepository rideRepo, PaymentService paymentService, WalletService walletService, DriversRepository driversRepo) {
        this.driversRepo = driversRepo;
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.paymentService = paymentService;
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
}
