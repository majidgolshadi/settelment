package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EntryTransactionRepository extends CrudRepository<EntityTransaction, String> {

    @Query(value = "SELECT sum(deposit - withdraw) FROM entry_transaction WHERE user_role = 'DRIVER'AND driver_id=:driverId AND created_date > :fromDate ", nativeQuery = true)
    long getDriverBalanceFromDate(@Param("driverId") String driverId, @Param("fromDate") Date date);

    @Query(value = "SELECT sum(withdraw) FROM entry_transaction  WHERE user_id=:driverId AND type= 'DRIVER_SETTLE' AND created_date > :fromDate", nativeQuery = true)
    long getDriverSettledFromDate(@Param("driverId") String driverId, @Param("fromDate") Date date);
}
