package ir.carpino.settlement.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entry_transaction")
public class EntryTransaction {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "controller_id")
    private String controllerId;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "driver_id")
    private String driverId;

    @Column(name = "from_user_id")
    private String fromUserId;

    @Column(name = "from_user_role")
    private String fromUserRole;

    @Column(name = "ride_id")
    private String rideId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "bank_transaction_id")
    private String bankTransactionId;

    @Column(name = "entry_transaction_id")
    private String entryTransactionId;

    @Column(name = "bank_account_info")
    private String bankAccountInfo;

    @Column(name = "couponId")
    private String couponId;

    @Column(name = "createdDate")
    private long createdDate;

    @Column(name = "modifiedDate")
    private long modifiedDate;

    @Column(name = "shabaNumber")
    private String shabaNumber;

    @Column(name = "promotionId")
    private String promotionId;

    private long balance;
    private long withdraw;
    private String type;
    private long deposit;
    private String description;
}
