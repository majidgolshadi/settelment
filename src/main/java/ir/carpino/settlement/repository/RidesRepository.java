package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mongo.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface RidesRepository extends MongoRepository<Ride, String> {
    public List<Ride> findRidesByStatusEqualsAndRideInfoRealStartRideDateGreaterThan(String status, Date date);
}
