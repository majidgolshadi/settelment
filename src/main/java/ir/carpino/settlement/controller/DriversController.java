package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.repository.DriversRepository;
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
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DriversController {

    private final WalletService walletService;
    private final RidesRepository rideRepo;
    private final DriversRepository driversRepo;
    private final PaymentService paymentService;

    @Value("#{'${settlement.settle-drivers}'.split(',')}")
    private List<String> settleDrivers;

    @Autowired
    public DriversController(RidesRepository rideRepo, DriversRepository driversRepo, PaymentService paymentService, WalletService walletService) {
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.driversRepo = driversRepo;
        this.paymentService = paymentService;
    }

    @PostMapping("/v1/settlement/driver/spc-active-from/{time}")
    public ResponseEntity specificActiveDriversSettlement(@PathVariable("time") long time) {
        Date date = new Date(time);

        if (date.before(new Date(1483228800))) {
            log.error(String.format("request rejected! calculation time defined from %s", date.toString()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        HashSet<String> drivers = new HashSet<>();

        log.info(String.format("driver settlement called from %s", date.toString()));
        rideRepo.findRidesByStatusEqualsAndCreatedDateAfter("COMPLETED", date)
                .stream()
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
                .stream()
                .filter(ride -> ride.getDriver() != null)
                .map(Ride::getDriver)
                .filter(driver -> drivers.add(driver.getId()))
                .collect(Collectors.toMap(
                        driver -> driver,
                        driver -> walletService.getUserBalance(driver.getId()))
                ).forEach(paymentService::settle);

        paymentService.flushPaymentBuffer();
        drivers.clear();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/v1/settlement/driver/force-revert")
    public ResponseEntity forceSpecDriver(@RequestBody Map<String, Long> drivers) {
        drivers.entrySet()
                .stream()
                .forEach(entry -> {
                    Optional<Driver> driverOpt = driversRepo.findById(entry.getKey());
                    if (!driverOpt.isPresent()) {
                        log.error("driver {} does not exist", entry.getKey());
                        return;
                    }

                    Driver driver = driverOpt.get();
                    long currentBalance = walletService.getUserBalance(driver.getId());
                    long revertBalance = entry.getValue();

                    log.info("revert balance for user {} with current balance {} and revert balance {}", driver.getId(), currentBalance, revertBalance);
                    paymentService.revertDriverWalletBalance(driver, revertBalance, currentBalance);
                });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
