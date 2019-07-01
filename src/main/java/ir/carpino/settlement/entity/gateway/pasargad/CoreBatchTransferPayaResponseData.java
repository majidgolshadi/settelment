package ir.carpino.settlement.entity.gateway.pasargad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreBatchTransferPayaResponseData {
    public String ReferenceNumber;
    public String DestinationBankName;
    public String State;
    public int Amount;
    public String BeneficiaryFullName;
    public String Description;
    public String DestShebaNumber;
    public String BillNumber;
    public String BatchNumber;
    public String InquiryName;
    public int BatchTransferId;
}
