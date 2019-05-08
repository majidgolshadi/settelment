package ir.carpino.settlement.entity.gateway.passargad;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class CoreBatchTransferPayaBaseInput implements Serializable {
    public String username;
    public String timestamp;
    public String sourceDepositNumber;
    public String fileUniqueIdentifier;
    public List<PaymentInfo> batchPayaItemInfos;
}
