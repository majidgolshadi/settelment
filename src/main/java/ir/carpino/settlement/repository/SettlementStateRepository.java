package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.SettlementState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SettlementStateRepository extends CrudRepository<SettlementState, String> {

    @Query(value = "update settlementState set transcationId = ?2 where userId = ?1", nativeQuery = true)
    public void setBankTransaction(String userId, String transactionId);
}
