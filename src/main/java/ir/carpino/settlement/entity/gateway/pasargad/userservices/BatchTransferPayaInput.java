
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BatchTransferPayaInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchTransferPayaInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="SourceDepositNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileUniqueIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransferMoneyBillNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BatchPayaItemInfos" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfBatchPayaItemInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchTransferPayaInput", propOrder = {
    "sourceDepositNumber",
    "userName",
    "fileUniqueIdentifier",
    "transferMoneyBillNumber",
    "batchPayaItemInfos"
})
public class BatchTransferPayaInput
    extends BaseInput
{

    @XmlElement(name = "SourceDepositNumber")
    protected String sourceDepositNumber;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "FileUniqueIdentifier")
    protected String fileUniqueIdentifier;
    @XmlElement(name = "TransferMoneyBillNumber")
    protected String transferMoneyBillNumber;
    @XmlElement(name = "BatchPayaItemInfos")
    protected ArrayOfBatchPayaItemInfo batchPayaItemInfos;

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
     * Gets the value of the fileUniqueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileUniqueIdentifier() {
        return fileUniqueIdentifier;
    }

    /**
     * Sets the value of the fileUniqueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileUniqueIdentifier(String value) {
        this.fileUniqueIdentifier = value;
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
     * Gets the value of the batchPayaItemInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBatchPayaItemInfo }
     *     
     */
    public ArrayOfBatchPayaItemInfo getBatchPayaItemInfos() {
        return batchPayaItemInfos;
    }

    /**
     * Sets the value of the batchPayaItemInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBatchPayaItemInfo }
     *     
     */
    public void setBatchPayaItemInfos(ArrayOfBatchPayaItemInfo value) {
        this.batchPayaItemInfos = value;
    }

}
