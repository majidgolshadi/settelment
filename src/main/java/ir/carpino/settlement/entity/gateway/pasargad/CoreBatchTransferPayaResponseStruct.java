package ir.carpino.settlement.entity.gateway.pasargad;

import java.util.List;

public class CoreBatchTransferPayaResponseStruct {
    public boolean IsSuccess;
    public String Message;
    public int MessageCode;
    public List<CoreBatchTransferPayaResponseData> Data;
}
