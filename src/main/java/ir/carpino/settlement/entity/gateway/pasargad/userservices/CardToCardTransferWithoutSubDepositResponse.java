
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardToCardTransferWithoutSubDepositResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cardToCardTransferWithoutSubDepositResult"
})
@XmlRootElement(name = "CardToCardTransferWithoutSubDepositResponse")
public class CardToCardTransferWithoutSubDepositResponse {

    @XmlElement(name = "CardToCardTransferWithoutSubDepositResult")
    protected String cardToCardTransferWithoutSubDepositResult;

    /**
     * Gets the value of the cardToCardTransferWithoutSubDepositResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardToCardTransferWithoutSubDepositResult() {
        return cardToCardTransferWithoutSubDepositResult;
    }

    /**
     * Sets the value of the cardToCardTransferWithoutSubDepositResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardToCardTransferWithoutSubDepositResult(String value) {
        this.cardToCardTransferWithoutSubDepositResult = value;
    }

}
