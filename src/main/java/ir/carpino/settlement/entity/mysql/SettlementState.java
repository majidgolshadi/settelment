package ir.carpino.settlement.entity.mysql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SettlementState {
    @Id
    private String userId;
    private String paymentId;
    private long balance;
    private String bankState;
    private Date updatedAt;
    private Date createdAt;

    public SettlementState(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;

        createdAt = new Date();
    }

    public SettlementState(String userId, String paymentId, long balance) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.balance = balance;

        updatedAt = new Date();
    }

    public void setBankState(String bankState) {
        this.bankState = bankState;
    }
}
