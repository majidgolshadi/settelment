package ir.carpino.settlement.entity.gateway.pasargad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTransactionMoneyStateResponseData {
    public String Key;
    public String Value;
}
