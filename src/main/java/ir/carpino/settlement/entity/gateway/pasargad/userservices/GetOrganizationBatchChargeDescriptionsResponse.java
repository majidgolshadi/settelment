
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
 *         &lt;element name="GetOrganizationBatchChargeDescriptionsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getOrganizationBatchChargeDescriptionsResult"
})
@XmlRootElement(name = "GetOrganizationBatchChargeDescriptionsResponse")
public class GetOrganizationBatchChargeDescriptionsResponse {

    @XmlElement(name = "GetOrganizationBatchChargeDescriptionsResult")
    protected String getOrganizationBatchChargeDescriptionsResult;

    /**
     * Gets the value of the getOrganizationBatchChargeDescriptionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetOrganizationBatchChargeDescriptionsResult() {
        return getOrganizationBatchChargeDescriptionsResult;
    }

    /**
     * Sets the value of the getOrganizationBatchChargeDescriptionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetOrganizationBatchChargeDescriptionsResult(String value) {
        this.getOrganizationBatchChargeDescriptionsResult = value;
    }

}
