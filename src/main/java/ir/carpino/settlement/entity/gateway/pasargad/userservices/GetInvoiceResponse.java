
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
 *         &lt;element name="GetInvoiceResult" type="{http://ibank.fanap.ir/SignatureService}ActionResult" minOccurs="0"/>
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
    "getInvoiceResult"
})
@XmlRootElement(name = "GetInvoiceResponse")
public class GetInvoiceResponse {

    @XmlElement(name = "GetInvoiceResult")
    protected ActionResult getInvoiceResult;

    /**
     * Gets the value of the getInvoiceResult property.
     * 
     * @return
     *     possible object is
     *     {@link ActionResult }
     *     
     */
    public ActionResult getGetInvoiceResult() {
        return getInvoiceResult;
    }

    /**
     * Sets the value of the getInvoiceResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionResult }
     *     
     */
    public void setGetInvoiceResult(ActionResult value) {
        this.getInvoiceResult = value;
    }

}
