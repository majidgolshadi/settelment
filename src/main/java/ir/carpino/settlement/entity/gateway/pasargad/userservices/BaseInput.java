
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaseInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrokerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InputSignature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OriginalAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseInput", propOrder = {
    "brokerId",
    "timestamp",
    "inputSignature",
    "clientAddress",
    "originalAddress"
})
@XmlSeeAlso({
    PayaListInput.class,
    TransactionInquiriesInput.class,
    GetBonCardTransactionsListInput.class,
    BatchTransferPayaInput.class,
    TransactionListInput.class,
    GetTransferStateInput.class,
    DepositIbanInfoInput.class,
    OrganizationBonCardStateInput.class,
    ChargeOrganizationBonCardsInput.class,
    ConfirmCustomerInfoInput.class,
    OrganizationBonCardsInput.class,
    BillNumberGeneratorDocInput.class,
    CardToCardListInput.class,
    BatchTransferMoneyInput.class,
    OrganizationChargeReportInput.class,
    GetBonCardTransactionsListOfOrganizationInput.class,
    OtherChequesDepositInput.class,
    WebServiceUserInput.class,
    ReverseWholeChargeOfOrganizationBonCardsInput.class,
    CardBalanceInput.class,
    BillPaymentByCardInput.class,
    CheckCustomerByContactAndIdNumberInput.class,
    SubmissionCheckInput.class,
    CardInfoInput.class,
    OrganizationAccountsInput.class,
    OrganizationBatchChargeDescriptionsInput.class,
    PayInstallmentInput.class,
    SearchInvoiceInput.class,
    CardInformationInput.class,
    UserTransferMoneyInput.class,
    CardToCardTransferInput.class,
    BillPaymentByDepsoitInput.class,
    DepositBillStructureInput.class
})
public abstract class BaseInput {

    @XmlElement(name = "BrokerId")
    protected String brokerId;
    @XmlElement(name = "Timestamp")
    protected String timestamp;
    @XmlElement(name = "InputSignature")
    protected String inputSignature;
    @XmlElement(name = "ClientAddress")
    protected String clientAddress;
    @XmlElement(name = "OriginalAddress")
    protected String originalAddress;

    /**
     * Gets the value of the brokerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrokerId() {
        return brokerId;
    }

    /**
     * Sets the value of the brokerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrokerId(String value) {
        this.brokerId = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimestamp(String value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the inputSignature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputSignature() {
        return inputSignature;
    }

    /**
     * Sets the value of the inputSignature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputSignature(String value) {
        this.inputSignature = value;
    }

    /**
     * Gets the value of the clientAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Sets the value of the clientAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientAddress(String value) {
        this.clientAddress = value;
    }

    /**
     * Gets the value of the originalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalAddress() {
        return originalAddress;
    }

    /**
     * Sets the value of the originalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalAddress(String value) {
        this.originalAddress = value;
    }

}
