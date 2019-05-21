package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.RidesRepository;
import ir.carpino.settlement.service.PaymentService;
import ir.carpino.settlement.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class DriversController {

    private final WalletService walletService;
    private final DriversRepository driversRepo;
    private final RidesRepository rideRepo;
    private final PaymentService paymentService;
    private final DateFormat dateFormat;

    @Autowired
    public DriversController(RidesRepository rideRepo, PaymentService paymentService, WalletService walletService, DriversRepository driversRepo) {
        this.driversRepo = driversRepo;
        this.walletService = walletService;
        this.rideRepo = rideRepo;
        this.paymentService = paymentService;

        dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SZ"); //2019/01/01 01:01:01:001
    }

    @PostMapping("/settlement/driver/active-from/{time}")
    public void activeDriversSettlement(@PathVariable("time") String time) {
        Date date = new Date();
        String stringTime = dateFormat.format(date);

        rideRepo.findActiveDriversId(time)
                .stream()
                .collect(Collectors.toMap(driversRepo::findById, walletService::getUserBalance))
                .forEach((driverInfo, balance) -> {
                    PaymentInfo paymentInfo = new PaymentInfo(
                            balance,
                            String.format("%s %s", driverInfo.getFirstName(), driverInfo.getLastName()),
                            String.format("carpino settlement with driver till %s", stringTime),
                            driverInfo.getBankAccountInfo().getShabaNumber(),
                            stringTime);

                    paymentService.settle(paymentInfo);
                });

        paymentService.flushSettlementBuffer();
    }
}
