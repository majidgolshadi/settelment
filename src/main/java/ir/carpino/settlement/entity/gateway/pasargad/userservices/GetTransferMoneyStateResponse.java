package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"getTransferMoneyStateResult"}
)
@XmlRootElement(
        name = "GetTransferMoneyStateResponse"
)
public class GetTransferMoneyStateResponse {
    @XmlElement(
            name = "GetTransferMoneyStateResult"
    )
    protected String getTransferMoneyStateResult;

    public GetTransferMoneyStateResponse() {
    }

    public String getGetTransferMoneyStateResult() {
        return this.getTransferMoneyStateResult;
    }

    public void setGetTransferMoneyStateResult(String value) {
        this.getTransferMoneyStateResult = value;
    }
}
