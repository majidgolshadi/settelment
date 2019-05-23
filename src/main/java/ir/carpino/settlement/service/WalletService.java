package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WalletService {

    @Autowired
    private EntryTransactionRepository entryTransactionRepo;

    public long getUserBalance(String userId) {
        EntityTransaction entityTransaction = entryTransactionRepo.getDriverBalance(userId);
        if (entityTransaction != null) {
            return entityTransaction.getBalance();
        }

        log.error(String.format("entry transaction balance for driver %s is null", userId));
        return 0;
    }
}
