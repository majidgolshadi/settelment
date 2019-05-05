package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriversRepository extends MongoRepository<Driver, Driver> {
    public Driver findById(String id);
}
