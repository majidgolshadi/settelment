package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mongo.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DriversRepository extends MongoRepository<Driver, String> {

}
