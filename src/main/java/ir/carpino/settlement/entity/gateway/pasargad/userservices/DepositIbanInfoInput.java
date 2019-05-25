
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DepositIbanInfoInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepositIbanInfoInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sheba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShenaseVariz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositIbanInfoInput", propOrder = {
    "username",
    "sheba",
    "shenaseVariz"
})
public class DepositIbanInfoInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "Sheba")
    protected String sheba;
    @XmlElement(name = "ShenaseVariz")
    protected String shenaseVariz;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the sheba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSheba() {
        return sheba;
    }

    /**
     * Sets the value of the sheba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSheba(String value) {
        this.sheba = value;
    }

    /**
     * Gets the value of the shenaseVariz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShenaseVariz() {
        return shenaseVariz;
    }

    /**
     * Sets the value of the shenaseVariz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShenaseVariz(String value) {
        this.shenaseVariz = value;
    }

}
