package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class DriversController {

    private final WalletService walletService;
    private final RidesRepository rideRepo;
    private final PaymentService paymentService;

    @Autowired
    public DriversController(RidesRepository rideRepo, PaymentService paymentService, WalletService walletService) {
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.paymentService = paymentService;
    }

    @PostMapping("/v1/settlement/driver/active-from/{time}")
    public ResponseEntity activeDriversSettlement(@PathVariable("time") long time) {
        Date date = new Date(time);

        // reject request before 2017
        if (date.before(new Date(1483228800))) {
            String errMsg = String.format("request rejected! calculation time defined from %s", date.toString());
            log.error(errMsg);
            return new ResponseEntity(errMsg, HttpStatus.BAD_REQUEST);
        }

        List<String> calculatedDrivers = new ArrayList<>();

        log.info(String.format("driver settlement called from %s", date.toString()));
        rideRepo.findRidesByStatusEqualsAndRideInfoRealStartRideDateAfter("COMPLETED", date)
                .stream()
                .filter(ride -> {
                    if (ride.getDriver() == null) {
                        log.error(String.format("completed ride %s with empty driver", ride.getId()));
                        return false;
                    }

                    if (calculatedDrivers.contains(ride.getDriver().getId())) {
                        return false;
                    }

                    calculatedDrivers.add(ride.getDriver().getId());
                    return true;
                })
                .collect(Collectors.toMap(
                        Ride::getDriver,
                        ride -> walletService.getUserBalance(ride.getDriver().getId()))
                ).forEach(paymentService::settle);

        paymentService.flushPaymentBuffer();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
