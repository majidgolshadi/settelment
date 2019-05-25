
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReverseWholeChargeOfOrganizationBonCardsInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReverseWholeChargeOfOrganizationBonCardsInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrganizationCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CardPANs" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="SrcDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReverseWholeChargeOfOrganizationBonCardsInput", propOrder = {
    "username",
    "organizationCode",
    "cardPANs",
    "srcDepositNumber"
})
public class ReverseWholeChargeOfOrganizationBonCardsInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "OrganizationCode")
    protected long organizationCode;
    @XmlElement(name = "CardPANs")
    protected ArrayOfString cardPANs;
    @XmlElement(name = "SrcDepositNumber")
    protected String srcDepositNumber;

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

}
