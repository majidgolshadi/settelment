
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="BillPaymentByCardResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "billPaymentByCardResult"
})
@XmlRootElement(name = "BillPaymentByCardResponse")
public class BillPaymentByCardResponse {

    @XmlElement(name = "BillPaymentByCardResult")
    protected String billPaymentByCardResult;

    /**
     * Gets the value of the billPaymentByCardResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPaymentByCardResult() {
        return billPaymentByCardResult;
    }

    /**
     * Sets the value of the billPaymentByCardResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPaymentByCardResult(String value) {
        this.billPaymentByCardResult = value;
    }

}
