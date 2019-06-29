package ir.carpino.settlement.entity.mysql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "user_id")
    private String userId;

    @Column(name = "payment_id")
    private String paymentId;

    private long balance;

    @Column(name = "bank_state")
    private String bankState;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public SettlementState(String userId, String paymentId, long balance) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.balance = balance;
    }
}
