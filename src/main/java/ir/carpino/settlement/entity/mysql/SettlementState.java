package ir.carpino.settlement.entity.mysql;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "settlement_state")
@EntityListeners(AuditingEntityListener.class)
public class SettlementState implements Serializable {
    @Id
    @Column(name = "payment_id", length = 40)
    private String paymentId;

    @Column(name = "user_id", length = 24, nullable = false)
    private String userId;

    private long balance;

    @Column(name = "bank_state", length = 16)
    private String bankState;

    @Column(name = "type", length = 16)
    private  String type;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public SettlementState(String paymentId, String userId, long balance) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.balance = balance;
    }
}
