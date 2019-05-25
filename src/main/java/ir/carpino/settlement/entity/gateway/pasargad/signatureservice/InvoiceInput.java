
package ir.carpino.settlement.entity.gateway.pasargad.signatureservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.fanap.ir/SignatureService}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="Username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sheba" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Count" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstIndex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsAscendingByDate" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsContainTimeFilter" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceInput", propOrder = {
    "username",
    "depositNumber",
    "sheba",
    "count",
    "startDate",
    "endDate",
    "firstIndex",
    "isAscendingByDate",
    "isContainTimeFilter"
})
public class InvoiceInput
    extends BaseInput
{

    @XmlElement(name = "Username")
    protected String username;
    @XmlElement(name = "DepositNumber")
    protected String depositNumber;
    @XmlElement(name = "Sheba")
    protected String sheba;
    @XmlElement(name = "Count")
    protected String count;
    @XmlElement(name = "StartDate")
    protected String startDate;
    @XmlElement(name = "EndDate")
    protected String endDate;
    @XmlElement(name = "FirstIndex")
    protected String firstIndex;
    @XmlElement(name = "IsAscendingByDate", required = true, type = Boolean.class, nillable = true)
    protected Boolean isAscendingByDate;
    @XmlElement(name = "IsContainTimeFilter")
    protected boolean isContainTimeFilter;

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
     * Gets the value of the depositNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositNumber() {
        return depositNumber;
    }

    /**
     * Sets the value of the depositNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositNumber(String value) {
        this.depositNumber = value;
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
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount(String value) {
        this.count = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the firstIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstIndex() {
        return firstIndex;
    }

    /**
     * Sets the value of the firstIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstIndex(String value) {
        this.firstIndex = value;
    }

    /**
     * Gets the value of the isAscendingByDate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAscendingByDate() {
        return isAscendingByDate;
    }

    /**
     * Sets the value of the isAscendingByDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAscendingByDate(Boolean value) {
        this.isAscendingByDate = value;
    }

    /**
     * Gets the value of the isContainTimeFilter property.
     * 
     */
    public boolean isIsContainTimeFilter() {
        return isContainTimeFilter;
    }

    /**
     * Sets the value of the isContainTimeFilter property.
     * 
     */
    public void setIsContainTimeFilter(boolean value) {
        this.isContainTimeFilter = value;
    }

}
