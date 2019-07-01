package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.EntryTransaction;
import ir.carpino.settlement.entity.mysql.SettlementState;
import ir.carpino.settlement.entity.mysql.SettlementStateBankState;
import ir.carpino.settlement.gateway.PasargadGateway;
import ir.carpino.settlement.repository.DriversRepository;
import ir.carpino.settlement.repository.EntryTransactionRepository;
import ir.carpino.settlement.repository.SettlementStateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Slf4j
@Component
public class PaymentService {
    private final SettlementStateRepository settlementStateRepo;
    private final EntryTransactionRepository entryTransactionRepo;
    private final DriversRepository driversRepo;
    private final PasargadGateway gateway;
    private final SettlementConfiguration config;
    private final DateFormat dateFormat;
    private final MongoTemplate mongoTemplate;
    private final Random random;

    private final String MASTER_OUTCOME_ID="MASTER_OUTCOME_ID0000000";
    private final String MASTER_OUTCOME_ROLE="MASTER_OUTCOME";
    private final String BILLING_DATE_FORMAT = "yyyyMMddHHmmssSSS";  //20190101010101001
    private final int min = 1_000;
    private final int max = 9_999;


    @Autowired
    public PaymentService(SettlementStateRepository settlementStateRepo, EntryTransactionRepository entryTransactionRepo,
                          DriversRepository driversRepo,
                          PasargadGateway pasargadGateway, SettlementConfiguration config, MongoTemplate mongoTemplate
    ) {
        this.settlementStateRepo = settlementStateRepo;
        this.entryTransactionRepo = entryTransactionRepo;
        this.driversRepo = driversRepo;
        this.gateway = pasargadGateway;
        this.config = config;
        this.mongoTemplate = mongoTemplate;

        random = new Random();
        dateFormat = new SimpleDateFormat(BILLING_DATE_FORMAT);
    }

    @PostConstruct
    public void initGatewayObserver() {
        gateway.setObserver(this);
    }

    private boolean passFilter(Driver driver, long balance) {
        if (driver.getBankAccountInfo() == null) {
            log.warn(String.format("driver %s bank info is empty", driver.getId()));
            return false;
        }

        if (driver.getBankAccountInfo().getBankName().equals(config.getSkipSettleForBank())) {
            log.warn(String.format("driver %s with bank name %s skipped", driver.getId(), driver.getBankAccountInfo().getBankName()));
            return false;
        }

        if (balance < config.getMinChargeToPay()) {
            log.warn(String.format("driver %s with balance %d is below balance limit", driver.getId(), balance));
            return false;
        }

        if (balance > config.getMaxChargeToPay()) {
            log.warn(String.format("[Fraud] driver %s with balance %d", driver.getId(), balance));
            return false;
        }

        return true;
    }

    public void settle(Driver driver, long balance) {
        settle(driver, balance, true);
    }

    public void settle(Driver driver, long balance, boolean decreaseFromBalance) {
        if (!passFilter(driver, balance)) {
            return;
        }

        String paymentId = String.format("Carpino%sRC%s", dateFormat.format(new Date()), random.nextInt((max - min) + 1) + min);

        log.info(String.format("settle %d for driver %s with payment id %s", balance, driver.getId(), paymentId));

        if (config.isTestMode()) {
            log.warn("payment skipped; app is in test mode");
            return;
        }

        settlementStateRepo.save(new SettlementState(paymentId, driver.getId(), balance));
        gateway.settle(driver, paymentId, balance);

        if (!decreaseFromBalance) {
            log.warn("!!DOES NOT DECREASE FROM WALLET BALANCE!!");
            return;
        }

        decreaseDriverWalletBalance(driver, balance);
    }

    public void updateSettleBankStatus(String paymentId) {
        Optional<SettlementState> stateOpt = settlementStateRepo.findById(paymentId);

        if (!stateOpt.isPresent()) {
            log.error("paymentId {} does not exist", paymentId);
            return;
        }

        SettlementState state = stateOpt.get();
        state.setBankState(SettlementStateBankState.REQUEST_SENT);
        settlementStateRepo.save(state);
    }

    public void flushPaymentBuffer() {
        gateway.flushBatchSettleBuffer();
    }

    @Scheduled(cron = "${settlement.payment.inquiry-cron}")
    public void bankInquiry() {
        log.info("cron job fired");

        if (config.isTestMode()) {
            log.warn("recheck skipped; app is in test mode");
            return;
        }

        if (!config.isRecheckSettle()) {
            log.warn("recheck skipped; recheck settle false");
            return;
        }

        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateIsNull();
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);
                Date date = new Date();

                settle.setBankState(statusCode);
                settle.setUpdatedAt(date);
                settlementStateRepo.save(settle);

                if (!statusCode.equals(SettlementStateBankState.RECEIVED)) {
                    Optional<Driver> driverOpt = driversRepo.findById(settle.getUserId());

                    if (!driverOpt.isPresent()) {
                        log.error("driver %s dose not find to revert money");
                        return;
                    }

                    Driver driver = driverOpt.get();
                    revertDriverWalletBalance(driver, settle.getBalance());
                    // update mongo balance
                }

            } catch (IOException | UnsuccessfulRequestException | InstantiationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void revertDriverWalletBalance(Driver driver, long balance) {
        Date date = new Date();
        EntryTransaction et = new EntryTransaction();
        et.setType("DRIVER_SETTLE");
        et.setFromUserId(MASTER_OUTCOME_ID);
        et.setFromUserRole(MASTER_OUTCOME_ROLE);
        et.setUserId(driver.getId());
        et.setUserRole("DRIVER");
        et.setDeposit(balance);
        et.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        et.setModifiedDate(date.getTime());

        EntryTransaction etRev = new EntryTransaction();
        etRev.setType("DRIVER_SETTLE");
        etRev.setFromUserId(driver.getId());
        etRev.setFromUserRole("DRIVER");
        etRev.setUserId(MASTER_OUTCOME_ID);
        etRev.setUserRole(MASTER_OUTCOME_ROLE);
        etRev.setWithdraw(balance);
        etRev.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        etRev.setModifiedDate(date.getTime());

        etRev.setEntryTransactionId(etRev.getId());
        etRev.setEntryTransactionId(et.getId());

        entryTransactionRepo.save(et);
        entryTransactionRepo.save(etRev);
    }

    private void decreaseDriverWalletBalance(Driver driver, long balance) {
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
