package ir.carpino.settlement.entity.gateway.pasargad;

import java.util.List;

public class ApiResponseList<T> {
    public boolean IsSuccess;
    public String Message;
    public int MessageCode;
    public List<T> Data;
}
