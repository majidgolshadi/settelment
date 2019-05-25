
package ir.carpino.settlement.entity.gateway.pasargad.signatureservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserDepositAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserDepositAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amounts" type="{http://ibank.fanap.ir/SignatureService}ArrayOfUserAmountInfo" minOccurs="0"/>
 *         &lt;element name="WithdrawableAmounts" type="{http://ibank.fanap.ir/SignatureService}ArrayOfUserAmountInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDepositAmount", propOrder = {
    "depositNumber",
    "amounts",
    "withdrawableAmounts"
})
public class UserDepositAmount {

    @XmlElement(name = "DepositNumber")
    protected String depositNumber;
    @XmlElement(name = "Amounts")
    protected ArrayOfUserAmountInfo amounts;
    @XmlElement(name = "WithdrawableAmounts")
    protected ArrayOfUserAmountInfo withdrawableAmounts;

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
     * Gets the value of the amounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUserAmountInfo }
     *     
     */
    public ArrayOfUserAmountInfo getAmounts() {
        return amounts;
    }

    /**
     * Sets the value of the amounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUserAmountInfo }
     *     
     */
    public void setAmounts(ArrayOfUserAmountInfo value) {
        this.amounts = value;
    }

    /**
     * Gets the value of the withdrawableAmounts property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUserAmountInfo }
     *     
     */
    public ArrayOfUserAmountInfo getWithdrawableAmounts() {
        return withdrawableAmounts;
    }

    /**
     * Sets the value of the withdrawableAmounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUserAmountInfo }
     *     
     */
    public void setWithdrawableAmounts(ArrayOfUserAmountInfo value) {
        this.withdrawableAmounts = value;
    }

}
