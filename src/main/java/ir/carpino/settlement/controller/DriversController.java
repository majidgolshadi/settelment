package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DriversController {

    private final WalletService walletService;
    private final RidesRepository rideRepo;
    private final PaymentService paymentService;

    @Value("#{'${settlement.settle-drivers}'.split(',')}")
    private List<String> settleDrivers;

    @Autowired
    public DriversController(RidesRepository rideRepo, PaymentService paymentService, WalletService walletService) {
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.paymentService = paymentService;
    }

    @PostMapping("/v1/settlement/driver/active-from/{time}")
    public ResponseEntity activeDriversSettlement(@PathVariable("time") long time) {
        Date date = new Date(time);

        if (date.before(new Date(1483228800))) {
            log.error(String.format("request rejected! calculation time defined from %s", date.toString()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        HashSet<String> drivers = new HashSet<>();

        log.info(String.format("driver settlement called from %s", date.toString()));
        rideRepo.findRidesByStatusEqualsAndCreatedDateAfter("COMPLETED", date)
                .parallelStream()
                .filter(ride -> ride.getDriver() != null)
                .map(Ride::getDriver)
                .filter(driver -> drivers.add(driver.getId()))
                .filter(driver -> settleDrivers.contains(driver.getId()))
                .collect(Collectors.toMap(
                        driver -> driver,
                        driver -> walletService.getUserBalance(driver.getId()))
                ).forEach(paymentService::settle);

        paymentService.flushPaymentBuffer();
        drivers.clear();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
