package ir.carpino.settlement.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankAccountInfo {
    private String bankName;
    private String shabaNumber;

    public String getShabaNumber() {
        return "IR" + shabaNumber;
    }
}
