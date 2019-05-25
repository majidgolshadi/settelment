
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherChequesDepositItemInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherChequesDepositItemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SayadSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChequeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChequeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnerBankCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnerBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChequeAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="IsIssuable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CurrencySwiftCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditorIban" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditorDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierNationalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierNationalityIsoCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CarrierLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Signet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BounceWithCertificate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsGovermentCreditor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsRealDebtor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsInstituteCommand" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TransferMoneyBillNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DebtorDepositNumberOrIBAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DebtorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RearImageBase64Format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FrontImageBase64Format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherChequesDepositItemInfo", propOrder = {
    "sayadSerialNumber",
    "chequeNumber",
    "chequeDate",
    "ownerBankCode",
    "ownerBranchCode",
    "chequeAmount",
    "isIssuable",
    "currencySwiftCode",
    "creditorIban",
    "creditorDepositNumber",
    "carrierNationalCode",
    "carrierNationalityIsoCode",
    "carrierName",
    "carrierLastName",
    "signet",
    "bounceWithCertificate",
    "isGovermentCreditor",
    "isRealDebtor",
    "isInstituteCommand",
    "transferMoneyBillNumber",
    "debtorDepositNumberOrIBAN",
    "debtorName",
    "rearImageBase64Format",
    "frontImageBase64Format"
})
public class OtherChequesDepositItemInfo {

    @XmlElement(name = "SayadSerialNumber")
    protected String sayadSerialNumber;
    @XmlElement(name = "ChequeNumber")
    protected String chequeNumber;
    @XmlElement(name = "ChequeDate")
    protected String chequeDate;
    @XmlElement(name = "OwnerBankCode")
    protected String ownerBankCode;
    @XmlElement(name = "OwnerBranchCode")
    protected String ownerBranchCode;
    @XmlElement(name = "ChequeAmount", required = true)
    protected BigDecimal chequeAmount;
    @XmlElement(name = "IsIssuable")
    protected boolean isIssuable;
    @XmlElement(name = "CurrencySwiftCode")
    protected String currencySwiftCode;
    @XmlElement(name = "CreditorIban")
    protected String creditorIban;
    @XmlElement(name = "CreditorDepositNumber")
    protected String creditorDepositNumber;
    @XmlElement(name = "CarrierNationalCode")
    protected String carrierNationalCode;
    @XmlElement(name = "CarrierNationalityIsoCode")
    protected String carrierNationalityIsoCode;
    @XmlElement(name = "CarrierName")
    protected String carrierName;
    @XmlElement(name = "CarrierLastName")
    protected String carrierLastName;
    @XmlElement(name = "Signet")
    protected boolean signet;
    @XmlElement(name = "BounceWithCertificate")
    protected String bounceWithCertificate;
    @XmlElement(name = "IsGovermentCreditor")
    protected boolean isGovermentCreditor;
    @XmlElement(name = "IsRealDebtor")
    protected boolean isRealDebtor;
    @XmlElement(name = "IsInstituteCommand")
    protected boolean isInstituteCommand;
    @XmlElement(name = "TransferMoneyBillNumber")
    protected String transferMoneyBillNumber;
    @XmlElement(name = "DebtorDepositNumberOrIBAN")
    protected String debtorDepositNumberOrIBAN;
    @XmlElement(name = "DebtorName")
    protected String debtorName;
    @XmlElement(name = "RearImageBase64Format")
    protected String rearImageBase64Format;
    @XmlElement(name = "FrontImageBase64Format")
    protected String frontImageBase64Format;

    /**
     * Gets the value of the sayadSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSayadSerialNumber() {
        return sayadSerialNumber;
    }

    /**
     * Sets the value of the sayadSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSayadSerialNumber(String value) {
        this.sayadSerialNumber = value;
    }

    /**
     * Gets the value of the chequeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeNumber() {
        return chequeNumber;
    }

    /**
     * Sets the value of the chequeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeNumber(String value) {
        this.chequeNumber = value;
    }

    /**
     * Gets the value of the chequeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeDate() {
        return chequeDate;
    }

    /**
     * Sets the value of the chequeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeDate(String value) {
        this.chequeDate = value;
    }

    /**
     * Gets the value of the ownerBankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerBankCode() {
        return ownerBankCode;
    }

    /**
     * Sets the value of the ownerBankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerBankCode(String value) {
        this.ownerBankCode = value;
    }

    /**
     * Gets the value of the ownerBranchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerBranchCode() {
        return ownerBranchCode;
    }

    /**
     * Sets the value of the ownerBranchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerBranchCode(String value) {
        this.ownerBranchCode = value;
    }

    /**
     * Gets the value of the chequeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }

    /**
     * Sets the value of the chequeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChequeAmount(BigDecimal value) {
        this.chequeAmount = value;
    }

    /**
     * Gets the value of the isIssuable property.
     * 
     */
    public boolean isIsIssuable() {
        return isIssuable;
    }

    /**
     * Sets the value of the isIssuable property.
     * 
     */
    public void setIsIssuable(boolean value) {
        this.isIssuable = value;
    }

    /**
     * Gets the value of the currencySwiftCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencySwiftCode() {
        return currencySwiftCode;
    }

    /**
     * Sets the value of the currencySwiftCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencySwiftCode(String value) {
        this.currencySwiftCode = value;
    }

    /**
     * Gets the value of the creditorIban property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorIban() {
        return creditorIban;
    }

    /**
     * Sets the value of the creditorIban property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorIban(String value) {
        this.creditorIban = value;
    }

    /**
     * Gets the value of the creditorDepositNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorDepositNumber() {
        return creditorDepositNumber;
    }

    /**
     * Sets the value of the creditorDepositNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorDepositNumber(String value) {
        this.creditorDepositNumber = value;
    }

    /**
     * Gets the value of the carrierNationalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierNationalCode() {
        return carrierNationalCode;
    }

    /**
     * Sets the value of the carrierNationalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierNationalCode(String value) {
        this.carrierNationalCode = value;
    }

    /**
     * Gets the value of the carrierNationalityIsoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierNationalityIsoCode() {
        return carrierNationalityIsoCode;
    }

    /**
     * Sets the value of the carrierNationalityIsoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierNationalityIsoCode(String value) {
        this.carrierNationalityIsoCode = value;
    }

    /**
     * Gets the value of the carrierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierName() {
        return carrierName;
    }

    /**
     * Sets the value of the carrierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierName(String value) {
        this.carrierName = value;
    }

    /**
     * Gets the value of the carrierLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierLastName() {
        return carrierLastName;
    }

    /**
     * Sets the value of the carrierLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierLastName(String value) {
        this.carrierLastName = value;
    }

    /**
     * Gets the value of the signet property.
     * 
     */
    public boolean isSignet() {
        return signet;
    }

    /**
     * Sets the value of the signet property.
     * 
     */
    public void setSignet(boolean value) {
        this.signet = value;
    }

    /**
     * Gets the value of the bounceWithCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBounceWithCertificate() {
        return bounceWithCertificate;
    }

    /**
     * Sets the value of the bounceWithCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBounceWithCertificate(String value) {
        this.bounceWithCertificate = value;
    }

    /**
     * Gets the value of the isGovermentCreditor property.
     * 
     */
    public boolean isIsGovermentCreditor() {
        return isGovermentCreditor;
    }

    /**
     * Sets the value of the isGovermentCreditor property.
     * 
     */
    public void setIsGovermentCreditor(boolean value) {
        this.isGovermentCreditor = value;
    }

    /**
     * Gets the value of the isRealDebtor property.
     * 
     */
    public boolean isIsRealDebtor() {
        return isRealDebtor;
    }

    /**
     * Sets the value of the isRealDebtor property.
     * 
     */
    public void setIsRealDebtor(boolean value) {
        this.isRealDebtor = value;
    }

    /**
     * Gets the value of the isInstituteCommand property.
     * 
     */
    public boolean isIsInstituteCommand() {
        return isInstituteCommand;
    }

    /**
     * Sets the value of the isInstituteCommand property.
     * 
     */
    public void setIsInstituteCommand(boolean value) {
        this.isInstituteCommand = value;
    }

    /**
     * Gets the value of the transferMoneyBillNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransferMoneyBillNumber() {
        return transferMoneyBillNumber;
    }

    /**
     * Sets the value of the transferMoneyBillNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransferMoneyBillNumber(String value) {
        this.transferMoneyBillNumber = value;
    }

    /**
     * Gets the value of the debtorDepositNumberOrIBAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebtorDepositNumberOrIBAN() {
        return debtorDepositNumberOrIBAN;
    }

    /**
     * Sets the value of the debtorDepositNumberOrIBAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebtorDepositNumberOrIBAN(String value) {
        this.debtorDepositNumberOrIBAN = value;
    }

    /**
     * Gets the value of the debtorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebtorName() {
        return debtorName;
    }

    /**
     * Sets the value of the debtorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebtorName(String value) {
        this.debtorName = value;
    }

    /**
     * Gets the value of the rearImageBase64Format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRearImageBase64Format() {
        return rearImageBase64Format;
    }

    /**
     * Sets the value of the rearImageBase64Format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRearImageBase64Format(String value) {
        this.rearImageBase64Format = value;
    }

    /**
     * Gets the value of the frontImageBase64Format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrontImageBase64Format() {
        return frontImageBase64Format;
    }

    /**
     * Sets the value of the frontImageBase64Format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrontImageBase64Format(String value) {
        this.frontImageBase64Format = value;
    }

}
