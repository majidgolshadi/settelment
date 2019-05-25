
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserTransferMoneyInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserTransferMoneyInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceSheba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestSheba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SourceComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DestComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserTransferMoneyInput", propOrder = {
    "username",
    "sourceDepositNumber",
    "sourceSheba",
    "destDepositNumber",
    "destSheba",
    "destBankCode",
    "destFirstName",
    "destLastName",
    "amount",
    "sourceComment",
    "destComment",
    "paymentId"
})
public class UserTransferMoneyInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "SourceDepositNumber")
    protected String sourceDepositNumber;
    @XmlElement(name = "SourceSheba")
    protected String sourceSheba;
    @XmlElement(name = "DestDepositNumber")
    protected String destDepositNumber;
    @XmlElement(name = "DestSheba")
    protected String destSheba;
    @XmlElement(name = "DestBankCode")
    protected String destBankCode;
    @XmlElement(name = "DestFirstName")
    protected String destFirstName;
    @XmlElement(name = "DestLastName")
    protected String destLastName;
    @XmlElement(name = "Amount")
    protected String amount;
    @XmlElement(name = "SourceComment")
    protected String sourceComment;
    @XmlElement(name = "DestComment")
    protected String destComment;
    @XmlElement(name = "PaymentId")
    protected String paymentId;

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
     * Gets the value of the sourceDepositNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDepositNumber() {
        return sourceDepositNumber;
    }

    /**
     * Sets the value of the sourceDepositNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDepositNumber(String value) {
        this.sourceDepositNumber = value;
    }

    /**
     * Gets the value of the sourceSheba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSheba() {
        return sourceSheba;
    }

    /**
     * Sets the value of the sourceSheba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSheba(String value) {
        this.sourceSheba = value;
    }

    /**
     * Gets the value of the destDepositNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestDepositNumber() {
        return destDepositNumber;
    }

    /**
     * Sets the value of the destDepositNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestDepositNumber(String value) {
        this.destDepositNumber = value;
    }

    /**
     * Gets the value of the destSheba property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestSheba() {
        return destSheba;
    }

    /**
     * Sets the value of the destSheba property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestSheba(String value) {
        this.destSheba = value;
    }

    /**
     * Gets the value of the destBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestBankCode() {
        return destBankCode;
    }

    /**
     * Sets the value of the destBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestBankCode(String value) {
        this.destBankCode = value;
    }

    /**
     * Gets the value of the destFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestFirstName() {
        return destFirstName;
    }

    /**
     * Sets the value of the destFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestFirstName(String value) {
        this.destFirstName = value;
    }

    /**
     * Gets the value of the destLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestLastName() {
        return destLastName;
    }

    /**
     * Sets the value of the destLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestLastName(String value) {
        this.destLastName = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the sourceComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceComment() {
        return sourceComment;
    }

    /**
     * Sets the value of the sourceComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceComment(String value) {
        this.sourceComment = value;
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
     * Gets the value of the paymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentId(String value) {
        this.paymentId = value;
    }

}
