
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrganizationChargeReportInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrganizationChargeReportInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ChargeDateFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChargeDateTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="MaxResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OrganizationCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrganizationChargeReportInput", propOrder = {
    "username",
    "accountCode",
    "chargeDateFrom",
    "chargeDateTo",
    "firstResult",
    "maxResult",
    "organizationCode"
})
public class OrganizationChargeReportInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "AccountCode")
    protected long accountCode;
    @XmlElement(name = "ChargeDateFrom")
    protected String chargeDateFrom;
    @XmlElement(name = "ChargeDateTo")
    protected String chargeDateTo;
    @XmlElement(name = "FirstResult")
    protected int firstResult;
    @XmlElement(name = "MaxResult")
    protected int maxResult;
    @XmlElement(name = "OrganizationCode")
    protected long organizationCode;

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
     * Gets the value of the chargeDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeDateFrom() {
        return chargeDateFrom;
    }

    /**
     * Sets the value of the chargeDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeDateFrom(String value) {
        this.chargeDateFrom = value;
    }

    /**
     * Gets the value of the chargeDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeDateTo() {
        return chargeDateTo;
    }

    /**
     * Sets the value of the chargeDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeDateTo(String value) {
        this.chargeDateTo = value;
    }

    /**
     * Gets the value of the firstResult property.
     * 
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * Sets the value of the firstResult property.
     * 
     */
    public void setFirstResult(int value) {
        this.firstResult = value;
    }

    /**
     * Gets the value of the maxResult property.
     * 
     */
    public int getMaxResult() {
        return maxResult;
    }

    /**
     * Sets the value of the maxResult property.
     * 
     */
    public void setMaxResult(int value) {
        this.maxResult = value;
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

}
