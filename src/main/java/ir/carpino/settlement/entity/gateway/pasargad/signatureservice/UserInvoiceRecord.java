
package ir.carpino.settlement.entity.gateway.pasargad.signatureservice;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserInvoiceRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserInvoiceRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransactionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeptorAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CreditorAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChqNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PaymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionSideLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionSideFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionSideDestDepositNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionSideSrcDepositNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInvoiceRecord", propOrder = {
    "transactionDate",
    "deptorAmount",
    "creditorAmount",
    "description",
    "docNumber",
    "chqNumber",
    "amount",
    "paymentId",
    "branchCode",
    "branchName",
    "transactionCode",
    "transactionSideLastName",
    "transactionSideFirstName",
    "transactionSideDestDepositNum",
    "transactionSideSrcDepositNum"
})
public class UserInvoiceRecord {

    @XmlElement(name = "TransactionDate")
    protected String transactionDate;
    @XmlElement(name = "DeptorAmount", required = true)
    protected BigDecimal deptorAmount;
    @XmlElement(name = "CreditorAmount", required = true)
    protected BigDecimal creditorAmount;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "DocNumber")
    protected String docNumber;
    @XmlElement(name = "ChqNumber")
    protected String chqNumber;
    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "PaymentId")
    protected String paymentId;
    @XmlElement(name = "BranchCode")
    protected String branchCode;
    @XmlElement(name = "BranchName")
    protected String branchName;
    @XmlElement(name = "TransactionCode")
    protected String transactionCode;
    @XmlElement(name = "TransactionSideLastName")
    protected String transactionSideLastName;
    @XmlElement(name = "TransactionSideFirstName")
    protected String transactionSideFirstName;
    @XmlElement(name = "TransactionSideDestDepositNum")
    protected String transactionSideDestDepositNum;
    @XmlElement(name = "TransactionSideSrcDepositNum")
    protected String transactionSideSrcDepositNum;

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDate(String value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the deptorAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeptorAmount() {
        return deptorAmount;
    }

    /**
     * Sets the value of the deptorAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeptorAmount(BigDecimal value) {
        this.deptorAmount = value;
    }

    /**
     * Gets the value of the creditorAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditorAmount() {
        return creditorAmount;
    }

    /**
     * Sets the value of the creditorAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditorAmount(BigDecimal value) {
        this.creditorAmount = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the docNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocNumber() {
        return docNumber;
    }

    /**
     * Sets the value of the docNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocNumber(String value) {
        this.docNumber = value;
    }

    /**
     * Gets the value of the chqNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChqNumber() {
        return chqNumber;
    }

    /**
     * Sets the value of the chqNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChqNumber(String value) {
        this.chqNumber = value;
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

    /**
     * Gets the value of the branchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * Sets the value of the branchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchCode(String value) {
        this.branchCode = value;
    }

    /**
     * Gets the value of the branchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Sets the value of the branchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranchName(String value) {
        this.branchName = value;
    }

    /**
     * Gets the value of the transactionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionCode() {
        return transactionCode;
    }

    /**
     * Sets the value of the transactionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionCode(String value) {
        this.transactionCode = value;
    }

    /**
     * Gets the value of the transactionSideLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSideLastName() {
        return transactionSideLastName;
    }

    /**
     * Sets the value of the transactionSideLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSideLastName(String value) {
        this.transactionSideLastName = value;
    }

    /**
     * Gets the value of the transactionSideFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSideFirstName() {
        return transactionSideFirstName;
    }

    /**
     * Sets the value of the transactionSideFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSideFirstName(String value) {
        this.transactionSideFirstName = value;
    }

    /**
     * Gets the value of the transactionSideDestDepositNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSideDestDepositNum() {
        return transactionSideDestDepositNum;
    }

    /**
     * Sets the value of the transactionSideDestDepositNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSideDestDepositNum(String value) {
        this.transactionSideDestDepositNum = value;
    }

    /**
     * Gets the value of the transactionSideSrcDepositNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSideSrcDepositNum() {
        return transactionSideSrcDepositNum;
    }

    /**
     * Sets the value of the transactionSideSrcDepositNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSideSrcDepositNum(String value) {
        this.transactionSideSrcDepositNum = value;
    }

}
