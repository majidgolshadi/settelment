package ir.carpino.settlement.entity.mysql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class EntityTransaction {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

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

    private int balance;
    private String couponId;
    private int createdDate;
    private int deposit;
    private String description;
    private long modifiedDate;
    private long withdraw;
    private String type;
    private String shabaNumber;
    private String promotionId;
}
