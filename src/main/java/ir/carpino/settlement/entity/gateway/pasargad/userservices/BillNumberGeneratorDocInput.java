
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillNumberGeneratorDocInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillNumberGeneratorDocInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IsDebtor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BillNumberGeneratorKeyValues" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfKeyValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillNumberGeneratorDocInput", propOrder = {
    "userName",
    "depositNumber",
    "amount",
    "isDebtor",
    "billNumberGeneratorKeyValues"
})
public class BillNumberGeneratorDocInput
    extends BaseInput
{

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "DepositNumber")
    protected String depositNumber;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "IsDebtor")
    protected boolean isDebtor;
    @XmlElement(name = "BillNumberGeneratorKeyValues")
    protected ArrayOfKeyValue billNumberGeneratorKeyValues;

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
     * Gets the value of the isDebtor property.
     * 
     */
    public boolean isIsDebtor() {
        return isDebtor;
    }

    /**
     * Sets the value of the isDebtor property.
     * 
     */
    public void setIsDebtor(boolean value) {
        this.isDebtor = value;
    }

    /**
     * Gets the value of the billNumberGeneratorKeyValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfKeyValue }
     *     
     */
    public ArrayOfKeyValue getBillNumberGeneratorKeyValues() {
        return billNumberGeneratorKeyValues;
    }

    /**
     * Sets the value of the billNumberGeneratorKeyValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfKeyValue }
     *     
     */
    public void setBillNumberGeneratorKeyValues(ArrayOfKeyValue value) {
        this.billNumberGeneratorKeyValues = value;
    }

}
