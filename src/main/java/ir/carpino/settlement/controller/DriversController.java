package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mongo.Ride;
import ir.carpino.settlement.entity.rest.CsvFile;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
public class DriversController {

    private final RidesRepository rideRepo;
    private final DriversRepository driversRepo;
    private final PaymentService paymentService;
    private final Wallet wallet;

    @Value("#{'${settlement.settle-drivers}'.split(',')}")
    private List<String> settleDrivers;

    @Autowired
    public DriversController(RidesRepository rideRepo, DriversRepository driversRepo, PaymentService paymentService, Wallet wallet) {
        this.rideRepo = rideRepo;
        this.driversRepo = driversRepo;
        this.paymentService = paymentService;
        this.wallet = wallet;
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
                .forEach(paymentService::settle);

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
                .forEach(paymentService::settle);

        paymentService.flushPaymentBuffer();
        drivers.clear();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/v1/settlement/driver/force-revert")
    public ResponseEntity forceRevertSpecDrivers(@RequestBody Map<String, Long> drivers) {
        drivers.forEach(wallet::revertDriverWalletBalance);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/v1/settlement/driver/force-revert/csv")
    public ResponseEntity csvForceRevertSpecDrivers(@RequestBody CsvFile csvFile) {
        Map<String, Long> fileContent = null;
        try {
            fileContent = readCsvFile(csvFile.getFileAddress());
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return forceRevertSpecDrivers(fileContent);
    }

    @PostMapping("/v1/settlement/driver/campaign")
    public ResponseEntity campaignDriverSettle(@RequestBody Map<String, Long> drivers) {
        drivers.forEach((driverId, balance) -> {
            Optional<Driver> driverOptional = driversRepo.findById(driverId);

            if (!driverOptional.isPresent()) {
                log.error("driverId {} does not exists", driverId);
                return;
            }

            Driver driver = driverOptional.get();
            paymentService.campainSettle(driver, balance);
        });

        paymentService.flushPaymentBuffer();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/v1/settlement/driver/campaign/csv")
    public ResponseEntity campaignDriverSettleCsv(@RequestBody CsvFile csvFile) {
        Map<String, Long> fileContent = null;
        try {
            fileContent = readCsvFile(csvFile.getFileAddress());
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return campaignDriverSettle(fileContent);
    }

    private Map<String, Long> readCsvFile(String fileAddress) throws IOException {
        if (fileAddress.isEmpty()) {
            throw new FileNotFoundException();
        }

        Map<String, Long> fileContent = new HashMap<>();

        Scanner scanner = new Scanner(new File(fileAddress));

        while (scanner.hasNextLine()) {
            String lineDate = scanner.nextLine();
            String[] values = lineDate.split(",");

            if (values.length < 2) {
                throw new IOException("invalid file content");
            }

            fileContent.put(values[0], Long.valueOf(values[1]));
        }

        return fileContent;
    }
}
