package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"coreBatchTransferPayaResult"}
)
@XmlRootElement(
        name = "CoreBatchTransferPayaResponse"
)
public class CoreBatchTransferPayaResponse {
    @XmlElement(
            name = "CoreBatchTransferPayaResult"
    )
    protected String coreBatchTransferPayaResult;

    public CoreBatchTransferPayaResponse() {
    }

    public String getCoreBatchTransferPayaResult() {
        return this.coreBatchTransferPayaResult;
    }

    public void setCoreBatchTransferPayaResult(String value) {
        this.coreBatchTransferPayaResult = value;
    }
}
