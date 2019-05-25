
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EChargeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EChargeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="SequenceNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CardSerial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RealAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PurchaseDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EChargeInfo", propOrder = {
    "chargeCode",
    "referenceNumber",
    "sequenceNumber",
    "cardSerial",
    "realAmount",
    "purchaseDateTime"
})
public class EChargeInfo {

    @XmlElement(name = "ChargeCode")
    protected String chargeCode;
    @XmlElement(name = "ReferenceNumber")
    protected long referenceNumber;
    @XmlElement(name = "SequenceNumber")
    protected int sequenceNumber;
    @XmlElement(name = "CardSerial")
    protected String cardSerial;
    @XmlElement(name = "RealAmount", required = true)
    protected BigDecimal realAmount;
    @XmlElement(name = "PurchaseDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar purchaseDateTime;

    /**
     * Gets the value of the chargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeCode() {
        return chargeCode;
    }

    /**
     * Sets the value of the chargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeCode(String value) {
        this.chargeCode = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     */
    public long getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     */
    public void setReferenceNumber(long value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the sequenceNumber property.
     * 
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sets the value of the sequenceNumber property.
     * 
     */
    public void setSequenceNumber(int value) {
        this.sequenceNumber = value;
    }

    /**
     * Gets the value of the cardSerial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardSerial() {
        return cardSerial;
    }

    /**
     * Sets the value of the cardSerial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardSerial(String value) {
        this.cardSerial = value;
    }

    /**
     * Gets the value of the realAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRealAmount() {
        return realAmount;
    }

    /**
     * Sets the value of the realAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRealAmount(BigDecimal value) {
        this.realAmount = value;
    }

    /**
     * Gets the value of the purchaseDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchaseDateTime() {
        return purchaseDateTime;
    }

    /**
     * Sets the value of the purchaseDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchaseDateTime(XMLGregorianCalendar value) {
        this.purchaseDateTime = value;
    }

}
