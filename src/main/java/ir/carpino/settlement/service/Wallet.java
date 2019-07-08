package ir.carpino.settlement.service;

import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.EntryTransaction;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Slf4j
@Service
public class Wallet {

    private final EntryTransactionRepository entryTransactionRepo;
    private final DriversRepository driversRepo;
    private final MongoTemplate mongoTemplate;

    private final String MASTER_OUTCOME_ID="MASTER_OUTCOME_ID0000000";
    private final String MASTER_OUTCOME_ROLE="MASTER_OUTCOME";

    @Autowired
    public Wallet(EntryTransactionRepository entryTransactionRepo,
                  DriversRepository driversRepo,
                  MongoTemplate mongoTemplate
    ) {
        this.entryTransactionRepo = entryTransactionRepo;
        this.driversRepo = driversRepo;

        this.mongoTemplate = mongoTemplate;
    }

    long getUserBalance(String userId) {
        long balance = entryTransactionRepo.getDriverBalance(userId);
        log.info(String.format("driver %s balance: %d", userId, balance));

        return balance;
    }

    /**
     * Calculate user account balance from the latest time that this value is calculated for
     * @param userId
     * @return
     */
//    long getUserBalanceFast(String userId) {
//        Date fromDate = new Date(631152000); // 1990/01/01
//
//        Optional<SettlementState> settlementState = settlementStateRepo.findById(userId);
//        if (settlementState.isPresent()) {
//            log.warn(userId, " driver payment history not found!");
//            fromDate = settlementState.get().getCreatedAt();
//        }
//
//        long walletBalance = entryTransactionRepo.getDriverBalanceFromDate(userId, fromDate);
//        long settled = entryTransactionRepo.getDriverSettledFromDate(userId, fromDate);
//        long balance = walletBalance - settled;
//
//        log.info(String.format("driver %s walletBalance: %d ,settled: %d, balance: %d", userId, walletBalance, settled, balance));
//
//        return balance;
//    }

    public void revertDriverWalletBalance(String userId, long payedMoney) {
        Optional<Driver> driverOpt = driversRepo.findById(userId);

        if (!driverOpt.isPresent()) {
            log.error("driver with id {} does not exists", userId);
            return;
        }

        Driver driver = driverOpt.get();
        long currentBalance = getUserBalance(userId);
        long balance = payedMoney + currentBalance;

        revertDriverWalletBalance(driver, payedMoney);
        mongoTemplate.updateFirst(query(where("id").is(driver.getId())), update("walletBalance", balance), Driver.class);
    }

    void revertDriverWalletBalance(Driver driver, long balance) {
        Date date = new Date();
        EntryTransaction et = new EntryTransaction();
        et.setType("CORRECTION_SETTLE");
        et.setFromUserId(MASTER_OUTCOME_ID);
        et.setFromUserRole(MASTER_OUTCOME_ROLE);
        et.setUserId(driver.getId());
        et.setUserRole("DRIVER");
        et.setDeposit(balance);
        et.setShabaNumber("0");
        et.setModifiedDate(date.getTime());

        EntryTransaction etRev = new EntryTransaction();
        etRev.setType("CORRECTION_SETTLE");
        etRev.setFromUserId(driver.getId());
        etRev.setFromUserRole("DRIVER");
        etRev.setUserId(MASTER_OUTCOME_ID);
        etRev.setUserRole(MASTER_OUTCOME_ROLE);
        etRev.setWithdraw(balance);
        etRev.setShabaNumber("0");
        etRev.setModifiedDate(date.getTime());

        etRev.setEntryTransactionId(etRev.getId());
        etRev.setEntryTransactionId(et.getId());

        entryTransactionRepo.save(et);
        entryTransactionRepo.save(etRev);
    }

    void decreaseDriverWalletBalance(Driver driver, long balance) {
        Date date = new Date();
        EntryTransaction et = new EntryTransaction();
        et.setType("DRIVER_SETTLE");
        et.setFromUserId(MASTER_OUTCOME_ID);
        et.setFromUserRole(MASTER_OUTCOME_ROLE);
        et.setUserId(driver.getId());
        et.setUserRole("DRIVER");
        et.setWithdraw(balance);
        et.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        et.setModifiedDate(date.getTime());
        et.setCreatedDate(date.getTime());

        EntryTransaction etRev = new EntryTransaction();
        etRev.setType("DRIVER_SETTLE");
        etRev.setFromUserId(driver.getId());
        etRev.setFromUserRole("DRIVER");
        etRev.setUserId(MASTER_OUTCOME_ID);
        etRev.setUserRole(MASTER_OUTCOME_ROLE);
        etRev.setDeposit(balance);
        etRev.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        etRev.setModifiedDate(date.getTime());
        etRev.setCreatedDate(date.getTime());

        etRev.setEntryTransactionId(etRev.getId());
        etRev.setEntryTransactionId(et.getId());

        entryTransactionRepo.save(et);
        entryTransactionRepo.save(etRev);

        mongoTemplate.updateFirst(query(where("id").is(driver.getId())), update("walletBalance", 0), Driver.class);
    }
}
