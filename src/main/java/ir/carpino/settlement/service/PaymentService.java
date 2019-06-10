package ir.carpino.settlement.service;

import ir.carpino.settlement.configuration.SettlementConfiguration;
import ir.carpino.settlement.entity.exception.UnsuccessfulRequestException;
import ir.carpino.settlement.entity.mongo.Driver;
import ir.carpino.settlement.entity.mysql.EntityTransaction;
import ir.carpino.settlement.entity.mysql.SettlementState;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private final String MASTER_OUTCOME_ID="MASTER_OUTCOME_ID0000000";
    private final String MASTER_OUTCOME_ROLE="MASTER_OUTCOME";
    private final String BILLING_DATE_FORMAT = "yyyyMMddHHmmssSSS";  //20190101010101001

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


        dateFormat = new SimpleDateFormat(BILLING_DATE_FORMAT);
    }

    @PostConstruct
    public void initGatewayObserver() {
        gateway.setObserver(this);
    }

    public void settle(Driver driver, long balance) {
        if (driver.getBankAccountInfo() == null) {
            log.warn(String.format("driver %s bank info is empty", driver.getId()));
            return;
        }

        if (driver.getBankAccountInfo().getBankName().equals(config.getSkipSettleForBank())) {
            log.warn(String.format("driver %s with bank name %s skipped", driver.getId(), driver.getBankAccountInfo().getBankName()));
            return;
        }

        if (balance < config.getMinChargeToPay()) {
            log.warn(String.format("driver %s with balance %d is below balance limit", driver.getId(), balance));
            return;
        }

        if (balance > config.getMaxChargeToPay()) {
            log.warn(String.format("[Fraud] driver %s with balance %d", driver.getId(), balance));
            return;
        }

        String paymentId = String.format("CarpinoAASS%sUSR%s", dateFormat.format(new Date()), driver.getId());

        log.info(String.format("settle %d for driver %s with payment id %s", balance, driver.getId(), paymentId));

        if (config.isTestMode()) {
            log.warn("payment skipped; app is in test mode");
            return;
        }

        settlementStateRepo.save(new SettlementState(driver.getId(), balance));
        gateway.settle(driver, paymentId, balance);
        decreaseDriverWalletBalance(driver, paymentId, balance);
    }

    /**
     * Store each settlement transaction id
     * @param paymentResult <userID, transactionId>
     */
    public void getPaymentResult(Map<String, String> paymentResult) {
        paymentResult.forEach(settlementStateRepo::setBankPaymentId);
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

        List<SettlementState> settlementStates = settlementStateRepo.findAllByBankStateIsNull();
        settlementStates.forEach(settle -> {
            try {
                String statusCode = gateway.inquirySettle(settle);
                Date date = new Date();

                settle.setBankState(statusCode);
                settle.setUpdatedAt(date);
                settlementStateRepo.save(settle);

                if (!statusCode.equals("received")) {
                    Optional<Driver> driverOpt = driversRepo.findById(settle.getUserId());

                    if (!driverOpt.isPresent()) {
                        log.error("driver %s dose not find to revert money");
                        return;
                    }

                    Driver driver = driverOpt.get();
                    revertDriverWalletBalance(driver, settle.getPaymentId(), settle.getBalance());
                    // update mongo balance
                }

            } catch (IOException | UnsuccessfulRequestException | InstantiationException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void revertDriverWalletBalance(Driver driver, String paymentId, long balance) {
        Date date = new Date();
        EntityTransaction et = new EntityTransaction();
        et.setType("DRIVER_SETTLE");
        et.setFromUserId(MASTER_OUTCOME_ID);
        et.setFromUserRole(MASTER_OUTCOME_ROLE);
        et.setUserId(driver.getId());
        et.setUserRole("DRIVER");
        et.setDeposit(balance);
        et.setBankTransactionId(paymentId);
        et.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        et.setModifiedDate(date.getTime());

        EntityTransaction etRev = new EntityTransaction();
        etRev.setType("DRIVER_SETTLE");
        etRev.setFromUserId(driver.getId());
        etRev.setFromUserRole("DRIVER");
        etRev.setUserId(MASTER_OUTCOME_ID);
        etRev.setUserRole(MASTER_OUTCOME_ROLE);
        etRev.setWithdraw(balance);
        etRev.setBankTransactionId(paymentId);
        etRev.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        etRev.setModifiedDate(date.getTime());

        etRev.setEntryTransactionId(etRev.getId());
        etRev.setEntryTransactionId(et.getId());

        entryTransactionRepo.save(et);
        entryTransactionRepo.save(etRev);
    }

    private void decreaseDriverWalletBalance(Driver driver, String paymentId, long balance) {
        Date date = new Date();
        EntityTransaction et = new EntityTransaction();
        et.setType("DRIVER_SETTLE");
        et.setFromUserId(MASTER_OUTCOME_ID);
        et.setFromUserRole(MASTER_OUTCOME_ROLE);
        et.setUserId(driver.getId());
        et.setUserRole("DRIVER");
        et.setWithdraw(balance);
        et.setBankTransactionId(paymentId);
        et.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        et.setModifiedDate(date.getTime());

        EntityTransaction etRev = new EntityTransaction();
        etRev.setType("DRIVER_SETTLE");
        etRev.setFromUserId(driver.getId());
        etRev.setFromUserRole("DRIVER");
        etRev.setUserId(MASTER_OUTCOME_ID);
        etRev.setUserRole(MASTER_OUTCOME_ROLE);
        etRev.setDeposit(balance);
        etRev.setBankTransactionId(paymentId);
        etRev.setShabaNumber(driver.getBankAccountInfo().getShabaNumberForDb());
        etRev.setModifiedDate(date.getTime());

        etRev.setEntryTransactionId(etRev.getId());
        etRev.setEntryTransactionId(et.getId());

        entryTransactionRepo.save(et);
        entryTransactionRepo.save(etRev);

        mongoTemplate.updateFirst(query(where("id").is(driver.getId())), update("walletBalance", 0), Driver.class);
    }
}
