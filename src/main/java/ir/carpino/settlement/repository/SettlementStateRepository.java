package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.SettlementState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SettlementStateRepository extends CrudRepository<SettlementState, String> {

    @Query(value = "update settlementState set paymentId = :paymentId where userId = :userId", nativeQuery = true)
    void setBankPaymentId(@Param("userId") String userId, @Param("paymentId") String paymentId);

    List<SettlementState> findAllByBankStateIsNull();
}
