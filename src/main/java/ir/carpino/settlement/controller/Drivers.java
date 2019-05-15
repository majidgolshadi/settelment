package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.gateway.pasargad.PaymentInfo;
import ir.carpino.settlement.entity.mysql.EntityTransaction;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import ir.carpino.settlement.repository.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Drivers {

    private final DriversRepository driverRepo;
    private final RidesRepository rideRepo;
    private final EntryTransactionRepository entryTransactionRepo;
    private final PasargadGateway pasargadGateway;
    private final DateFormat dateFormat;

    @Autowired
    public Drivers(DriversRepository driverRepo, RidesRepository rideRepo, PasargadGateway pasargadGateway, EntryTransactionRepository entryTransactionRepo) {
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
        this.entryTransactionRepo = entryTransactionRepo;
        this.pasargadGateway = pasargadGateway;

        dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SZ"); //2019/01/01 01:01:01:001
    }

    @PostMapping("/settlement/driver/active")
    public void activeDriversSettlement() {
        List<PaymentInfo> driverPaymentInfo = new ArrayList<>();
        List<String> driverIds = rideRepo.findActiveDriversId("200000");
        Date date = new Date();
        String stringTime = dateFormat.format(date);
        date.toString()

        driverIds.parallelStream()
                .forEach(id -> {
                    Driver driverInfo = driverRepo.findById(id);
                    EntityTransaction entityTransaction = entryTransactionRepo.getDriverBalance(id);

                    PaymentInfo paymentInfo = new PaymentInfo(entityTransaction.getBalance(),
                            String.format("%s %s", driverInfo.getFirstName(), driverInfo.getLastName()),
                            String.format("carpino settlement with driver till %s", stringTime),
                            driverInfo.getBankAccountInfo().getShabaNumber(),
                            "");

                    driverPaymentInfo.add(paymentInfo);
                });

        // get drivers balance
        try {
            pasargadGateway.batchPayment(driverPaymentInfo);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
