package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mongo.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface RidesRepository extends MongoRepository<Ride, String> {
    List<Ride> findRidesByStatusEqualsAndRideInfoRealStartRideDateGreaterThan(String status, Date date);
}
