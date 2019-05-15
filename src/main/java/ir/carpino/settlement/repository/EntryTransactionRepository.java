package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTransactionRepository extends CrudRepository<EntityTransaction, String> {

    @Query(value = "select * from entry_transaction where driver_id = ?1 order_by createdDate DESC limit 1", nativeQuery = true)
    public EntityTransaction getDriverBalance(String driverId);
}
