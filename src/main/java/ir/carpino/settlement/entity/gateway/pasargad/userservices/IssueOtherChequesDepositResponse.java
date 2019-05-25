
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
 *         &lt;element name="IssueOtherChequesDepositResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "issueOtherChequesDepositResult"
})
@XmlRootElement(name = "IssueOtherChequesDepositResponse")
public class IssueOtherChequesDepositResponse {

    @XmlElement(name = "IssueOtherChequesDepositResult")
    protected String issueOtherChequesDepositResult;

    /**
     * Gets the value of the issueOtherChequesDepositResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueOtherChequesDepositResult() {
        return issueOtherChequesDepositResult;
    }

    /**
     * Sets the value of the issueOtherChequesDepositResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueOtherChequesDepositResult(String value) {
        this.issueOtherChequesDepositResult = value;
    }

}
