package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.SettlementState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SettlementStateRepository extends CrudRepository<SettlementState, String> {

    @Query(value = "update settlementState set transcationId = :transactionId where userId = :userId", nativeQuery = true)
    public void setBankTransaction(@Param("userId") String userId, @Param("transactionId") String transactionId);

    public List<SettlementState> findAllByBankStateIsNull();
}
