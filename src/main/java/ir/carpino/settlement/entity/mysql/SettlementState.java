package ir.carpino.settlement.entity.mysql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "settlement_state")
public class SettlementState {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "payment_id")
    private String paymentId;

    private long balance;

    @Column(name = "bank_state")
    private String bankState;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_at")
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
