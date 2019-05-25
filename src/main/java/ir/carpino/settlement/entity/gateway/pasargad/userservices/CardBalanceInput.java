
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardBalanceInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardBalanceInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cvv2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpiredMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExpiredYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardBalanceInput", propOrder = {
    "username",
    "cardNumber",
    "pin2",
    "cvv2",
    "expiredMonth",
    "expiredYear",
    "email"
})
public class CardBalanceInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "CardNumber")
    protected String cardNumber;
    @XmlElement(name = "Pin2")
    protected String pin2;
    @XmlElement(name = "Cvv2")
    protected String cvv2;
    @XmlElement(name = "ExpiredMonth")
    protected int expiredMonth;
    @XmlElement(name = "ExpiredYear")
    protected int expiredYear;
    @XmlElement(name = "Email")
    protected String email;

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
     * Gets the value of the cardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the value of the cardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardNumber(String value) {
        this.cardNumber = value;
    }

    /**
     * Gets the value of the pin2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * Sets the value of the pin2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin2(String value) {
        this.pin2 = value;
    }

    /**
     * Gets the value of the cvv2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvv2() {
        return cvv2;
    }

    /**
     * Sets the value of the cvv2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvv2(String value) {
        this.cvv2 = value;
    }

    /**
     * Gets the value of the expiredMonth property.
     * 
     */
    public int getExpiredMonth() {
        return expiredMonth;
    }

    /**
     * Sets the value of the expiredMonth property.
     * 
     */
    public void setExpiredMonth(int value) {
        this.expiredMonth = value;
    }

    /**
     * Gets the value of the expiredYear property.
     * 
     */
    public int getExpiredYear() {
        return expiredYear;
    }

    /**
     * Sets the value of the expiredYear property.
     * 
     */
    public void setExpiredYear(int value) {
        this.expiredYear = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

}
