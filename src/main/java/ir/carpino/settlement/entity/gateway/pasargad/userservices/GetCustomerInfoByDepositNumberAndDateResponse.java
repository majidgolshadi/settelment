
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
 *         &lt;element name="GetCustomerInfoByDepositNumberAndDateResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getCustomerInfoByDepositNumberAndDateResult"
})
@XmlRootElement(name = "GetCustomerInfoByDepositNumberAndDateResponse")
public class GetCustomerInfoByDepositNumberAndDateResponse {

    @XmlElement(name = "GetCustomerInfoByDepositNumberAndDateResult")
    protected String getCustomerInfoByDepositNumberAndDateResult;

    /**
     * Gets the value of the getCustomerInfoByDepositNumberAndDateResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCustomerInfoByDepositNumberAndDateResult() {
        return getCustomerInfoByDepositNumberAndDateResult;
    }

    /**
     * Sets the value of the getCustomerInfoByDepositNumberAndDateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCustomerInfoByDepositNumberAndDateResult(String value) {
        this.getCustomerInfoByDepositNumberAndDateResult = value;
    }

}
