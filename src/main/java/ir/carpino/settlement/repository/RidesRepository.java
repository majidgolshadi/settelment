package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RidesRepository extends MongoRepository<Ride, String> {

    @Query("{$and: [{status: 'COMPLETED'}, {rideInfo.realStartRideDate: {$gte: {$date: ?1}}}]}")
    public List<String> findActiveDriversId(String date);
}
