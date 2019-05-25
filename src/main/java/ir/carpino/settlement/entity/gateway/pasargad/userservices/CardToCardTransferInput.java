
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardToCardTransferInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardToCardTransferInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SrcCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SrcDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Cvv2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpireMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExpireYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="DestCardNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthorizationCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SrcComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WithReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="JsonData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardToCardTransferInput", propOrder = {
    "userName",
    "srcCardNumber",
    "srcDepositNumber",
    "password",
    "cvv2",
    "expireMonth",
    "expireYear",
    "destCardNumber",
    "amount",
    "email",
    "phoneNumber",
    "authorizationCode",
    "cardName",
    "srcComment",
    "destComment",
    "withReferenceNumber",
    "jsonData"
})
public class CardToCardTransferInput
    extends BaseInput
{

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "SrcCardNumber")
    protected String srcCardNumber;
    @XmlElement(name = "SrcDepositNumber")
    protected String srcDepositNumber;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "Cvv2")
    protected String cvv2;
    @XmlElement(name = "ExpireMonth")
    protected int expireMonth;
    @XmlElement(name = "ExpireYear")
    protected int expireYear;
    @XmlElement(name = "DestCardNumber")
    protected String destCardNumber;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "AuthorizationCode")
    protected long authorizationCode;
    @XmlElement(name = "CardName")
    protected String cardName;
    @XmlElement(name = "SrcComment")
    protected String srcComment;
    @XmlElement(name = "DestComment")
    protected String destComment;
    @XmlElement(name = "WithReferenceNumber")
    protected boolean withReferenceNumber;
    @XmlElement(name = "JsonData")
    protected String jsonData;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the srcCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcCardNumber() {
        return srcCardNumber;
    }

    /**
     * Sets the value of the srcCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcCardNumber(String value) {
        this.srcCardNumber = value;
    }

    /**
     * Gets the value of the srcDepositNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcDepositNumber() {
        return srcDepositNumber;
    }

    /**
     * Sets the value of the srcDepositNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcDepositNumber(String value) {
        this.srcDepositNumber = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the expireMonth property.
     * 
     */
    public int getExpireMonth() {
        return expireMonth;
    }

    /**
     * Sets the value of the expireMonth property.
     * 
     */
    public void setExpireMonth(int value) {
        this.expireMonth = value;
    }

    /**
     * Gets the value of the expireYear property.
     * 
     */
    public int getExpireYear() {
        return expireYear;
    }

    /**
     * Sets the value of the expireYear property.
     * 
     */
    public void setExpireYear(int value) {
        this.expireYear = value;
    }

    /**
     * Gets the value of the destCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestCardNumber() {
        return destCardNumber;
    }

    /**
     * Sets the value of the destCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestCardNumber(String value) {
        this.destCardNumber = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
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

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the authorizationCode property.
     * 
     */
    public long getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * Sets the value of the authorizationCode property.
     * 
     */
    public void setAuthorizationCode(long value) {
        this.authorizationCode = value;
    }

    /**
     * Gets the value of the cardName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the value of the cardName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardName(String value) {
        this.cardName = value;
    }

    /**
     * Gets the value of the srcComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcComment() {
        return srcComment;
    }

    /**
     * Sets the value of the srcComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcComment(String value) {
        this.srcComment = value;
    }

    /**
     * Gets the value of the destComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestComment() {
        return destComment;
    }

    /**
     * Sets the value of the destComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestComment(String value) {
        this.destComment = value;
    }

    /**
     * Gets the value of the withReferenceNumber property.
     * 
     */
    public boolean isWithReferenceNumber() {
        return withReferenceNumber;
    }

    /**
     * Sets the value of the withReferenceNumber property.
     * 
     */
    public void setWithReferenceNumber(boolean value) {
        this.withReferenceNumber = value;
    }

    /**
     * Gets the value of the jsonData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * Sets the value of the jsonData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJsonData(String value) {
        this.jsonData = value;
    }

}
