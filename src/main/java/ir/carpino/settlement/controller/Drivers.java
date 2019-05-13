package ir.carpino.settlement.controller;

import ir.carpino.settlement.entity.Driver;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Drivers {

    private final DriversRepository driverRepo;
    private final RidesRepository rideRepo;
    private final PasargadGateway pasargadGateway;

    @Autowired
    public Drivers(DriversRepository driverRepo, RidesRepository rideRepo, PasargadGateway pasargadGateway) {
        this.driverRepo = driverRepo;
        this.rideRepo = rideRepo;
        this.pasargadGateway = pasargadGateway;
    }

    @PostMapping("/settlement/driver/active")
    public void activeDriversSettlement() {
        List<Driver> driverInfo = new ArrayList<>();
        List<String> driverIds = rideRepo.findActiveDriversId("200000");

        driverIds.parallelStream()
                .forEach(id -> driverInfo.add(driverRepo.findById(id)));

        // get drivers balance
        pasargadGateway.CoreBatchTransferPayaRequest(driverInfo);
    }
}
