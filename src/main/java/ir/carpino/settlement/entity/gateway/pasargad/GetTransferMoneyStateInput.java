package ir.carpino.settlement.entity.gateway.pasargad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class GetTransferMoneyStateInput {
    private String Username;
    private Date Date;
    private String Timestamp;
    private String PaymentId;
}
