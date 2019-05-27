package ir.carpino.settlement.entity.gateway.pasargad;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetTransferMoneyStateInput {
    private String Username;
    private String Date;
    private String Timestamp;
    private String PaymentId;
}
