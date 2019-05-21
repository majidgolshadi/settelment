package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.mysql.EntityTransaction;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    private EntryTransactionRepository entryTransactionRepo;

    public HashMap<String, Long> getUsersBalance(List<String> userIds) {
        HashMap<String, Long> result = new HashMap<>();
        userIds.parallelStream().forEach(id ->result.put(id, getUserBalance(id)));

        return result;
    }

    public long getUserBalance(String userId) {
        EntityTransaction entityTransaction = entryTransactionRepo.getDriverBalance(userId);
        return entityTransaction.getBalance();
    }
}
