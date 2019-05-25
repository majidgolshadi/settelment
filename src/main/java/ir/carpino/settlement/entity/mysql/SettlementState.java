package ir.carpino.settlement.entity.mysql;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
public class SettlementState {
    @Id
    private String userId;
    private String transactionId;
    private long balance;
    private String bankState;
    private Date updatedAt;
    private Date createdAt;

    public SettlementState(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;

        createdAt = new Date();
    }

    public SettlementState(String userId, String transactionId, long balance) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.balance = balance;

        updatedAt = new Date();
    }

    public void setBankState(String bankState) {
        this.bankState = bankState;
    }
}
