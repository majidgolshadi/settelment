package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"request", "signature"}
)
@XmlRootElement(
        name = "CoreBatchTransferPaya"
)
public class CoreBatchTransferPaya {
    protected String request;
    protected String signature;

    public CoreBatchTransferPaya() {
    }

    public String getRequest() {
        return this.request;
    }

    public void setRequest(String value) {
        this.request = value;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String value) {
        this.signature = value;
    }
}