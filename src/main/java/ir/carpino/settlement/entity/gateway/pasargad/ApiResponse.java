package ir.carpino.settlement.entity.gateway.pasargad;

public class ApiResponse<T> {
    public boolean IsSuccess;
    public String Message;
    public int MessageCode;
    public T Data;
}
