
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
 *         &lt;element name="GetBonCardOrganizationAccountsResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getBonCardOrganizationAccountsResult"
})
@XmlRootElement(name = "GetBonCardOrganizationAccountsResponse")
public class GetBonCardOrganizationAccountsResponse {

    @XmlElement(name = "GetBonCardOrganizationAccountsResult")
    protected String getBonCardOrganizationAccountsResult;

    /**
     * Gets the value of the getBonCardOrganizationAccountsResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBonCardOrganizationAccountsResult() {
        return getBonCardOrganizationAccountsResult;
    }

    /**
     * Sets the value of the getBonCardOrganizationAccountsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBonCardOrganizationAccountsResult(String value) {
        this.getBonCardOrganizationAccountsResult = value;
    }

}
