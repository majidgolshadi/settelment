package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTransactionRepository extends CrudRepository<EntityTransaction, String> {

    @Query(value = "SELECT * FROM entity_transaction WHERE driver_id=:driverId ORDER BY created_date DESC limit 1", nativeQuery = true)
    public EntityTransaction getDriverBalance(@Param("driverId") String driverId);
}
