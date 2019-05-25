
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
 *         &lt;element name="CreateWebServiceUserResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "createWebServiceUserResult"
})
@XmlRootElement(name = "CreateWebServiceUserResponse")
public class CreateWebServiceUserResponse {

    @XmlElement(name = "CreateWebServiceUserResult")
    protected String createWebServiceUserResult;

    /**
     * Gets the value of the createWebServiceUserResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreateWebServiceUserResult() {
        return createWebServiceUserResult;
    }

    /**
     * Sets the value of the createWebServiceUserResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreateWebServiceUserResult(String value) {
        this.createWebServiceUserResult = value;
    }

}
