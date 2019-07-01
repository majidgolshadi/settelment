package ir.carpino.settlement.entity.gateway.pasargad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonIgnoreProperties
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
