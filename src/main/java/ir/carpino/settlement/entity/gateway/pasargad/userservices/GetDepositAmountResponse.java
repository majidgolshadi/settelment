
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ir.carpino.settlement.entity.gateway.pasargad.signatureservice.ActionResult;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetDepositAmountResult" type="{http://ibank.fanap.ir/SignatureService}ActionResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getDepositAmountResult"
})
@XmlRootElement(name = "GetDepositAmountResponse")
public class GetDepositAmountResponse {

    @XmlElement(name = "GetDepositAmountResult")
    protected ActionResult getDepositAmountResult;

    /**
     * Gets the value of the getDepositAmountResult property.
     * 
     * @return
     *     possible object is
     *     {@link ActionResult }
     *     
     */
    public ActionResult getGetDepositAmountResult() {
        return getDepositAmountResult;
    }

    /**
     * Sets the value of the getDepositAmountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionResult }
     *     
     */
    public void setGetDepositAmountResult(ActionResult value) {
        this.getDepositAmountResult = value;
    }

}
