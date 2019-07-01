package ir.carpino.settlement.repository;

import ir.carpino.settlement.entity.mysql.SettlementState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettlementStateRepository extends CrudRepository<SettlementState, String> {

    List<SettlementState> findAllByBankStateIsNull();
    List<SettlementState> findAllByBankStateEquals(String state);
}
