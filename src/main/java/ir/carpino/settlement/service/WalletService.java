package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import ir.carpino.settlement.repository.SettlementStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class WalletService {

    private final EntryTransactionRepository entryTransactionRepo;
    private final SettlementStateRepository settlementStateRepo;

    @Autowired
    public WalletService(EntryTransactionRepository entryTransactionRepo, SettlementStateRepository settlementStateRepo) {
        this.entryTransactionRepo = entryTransactionRepo;
        this.settlementStateRepo = settlementStateRepo;
    }

    /**
     * Calculate user account balance from the latest time that this value is calculated for
     * @param userId
     * @return
     */
    public long getUserBalance(String userId) {
        Date fromDate = new Date(631152000); // 1990/01/01

        Optional<SettlementState> settlementState = settlementStateRepo.findById(userId);
        if (settlementState.isPresent()) {
            log.warn(userId, " driver payment history not found!");
            fromDate = settlementState.get().getCreatedAt();
        }

        return entryTransactionRepo.getDriverBalanceFromDate(userId, fromDate) - entryTransactionRepo.getDriverSettledFromDate(userId, fromDate);
    }
}
