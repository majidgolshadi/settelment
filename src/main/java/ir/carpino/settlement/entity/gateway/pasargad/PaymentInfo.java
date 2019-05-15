package ir.carpino.settlement.entity.gateway.pasargad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PaymentInfo implements Serializable {
    public String amount;
    public String beneficiaryFullName;
    public String description;
    public String destShebaNumber;
    public String billNumber;
}
