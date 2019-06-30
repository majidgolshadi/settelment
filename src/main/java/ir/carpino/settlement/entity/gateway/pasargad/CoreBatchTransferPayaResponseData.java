package ir.carpino.settlement.entity.gateway.pasargad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class CoreBatchTransferPayaResponseData {
    private String ReferenceNumber;
    private String DestinationBankName;
    private String State;
    private int Amount;
    private String BeneficiaryFullName;
    private String Description;
    private String DestShebaNumber;
    private String BillNumber;
    private String BatchNumber;
    private String InquiryName;
    private int BatchTransferId;
}
