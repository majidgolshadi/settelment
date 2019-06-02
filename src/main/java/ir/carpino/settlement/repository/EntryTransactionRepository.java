package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryTransactionRepository extends CrudRepository<EntityTransaction, String> {

    @Query(value = "SELECT ifnull(sum(deposit - withdraw), 0) FROM entry_transaction WHERE user_role = 'DRIVER'AND driver_id=:driverId", nativeQuery = true)
    long getDriverBalanceFromDate(@Param("driverId") String driverId);

    @Query(value = "SELECT ifnull(sum(withdraw), 0) FROM entry_transaction  WHERE user_id=:driverId AND type='DRIVER_SETTLE'", nativeQuery = true)
    long getDriverSettledFromDate(@Param("driverId") String driverId);
}
