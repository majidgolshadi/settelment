
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChargeOrganizationBonCardsInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeOrganizationBonCardsInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="SourceDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrganizationCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CardPANs" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="Amounts" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeOrganizationBonCardsInput", propOrder = {
    "username",
    "accountCode",
    "sourceDepositNumber",
    "organizationCode",
    "cardPANs",
    "amounts"
})
public class ChargeOrganizationBonCardsInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "AccountCode")
    protected long accountCode;
    @XmlElement(name = "SourceDepositNumber")
    protected String sourceDepositNumber;
    @XmlElement(name = "OrganizationCode")
    protected long organizationCode;
    @XmlElement(name = "CardPANs")
    protected ArrayOfString cardPANs;
    @XmlElement(name = "Amounts")
    protected ArrayOfString amounts;

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
     * Gets the value of the accountCode property.
     * 
     */
    public long getAccountCode() {
        return accountCode;
    }

    /**
     * Sets the value of the accountCode property.
     * 
     */
    public void setAccountCode(long value) {
        this.accountCode = value;
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
     * Gets the value of the organizationCode property.
     * 
     */
    public long getOrganizationCode() {
        return organizationCode;
    }

    /**
     * Sets the value of the organizationCode property.
     * 
     */
    public void setOrganizationCode(long value) {
        this.organizationCode = value;
    }

    /**
     * Gets the value of the cardPANs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getCardPANs() {
        return cardPANs;
    }

    /**
     * Sets the value of the cardPANs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setCardPANs(ArrayOfString value) {
        this.cardPANs = value;
    }

    /**
     * Gets the value of the amounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getAmounts() {
        return amounts;
    }

    /**
     * Sets the value of the amounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setAmounts(ArrayOfString value) {
        this.amounts = value;
    }

}
