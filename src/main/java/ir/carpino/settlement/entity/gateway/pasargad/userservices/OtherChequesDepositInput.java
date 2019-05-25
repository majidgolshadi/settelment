
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherChequesDepositInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherChequesDepositInput">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ibank.toranj.fanap.co.ir/UserServices}BaseInput">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ChequesDepositItemInfos" type="{http://ibank.toranj.fanap.co.ir/UserServices}ArrayOfOtherChequesDepositItemInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherChequesDepositInput", propOrder = {
    "userName",
    "chequesDepositItemInfos"
})
public class OtherChequesDepositInput
    extends BaseInput
{

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "ChequesDepositItemInfos")
    protected ArrayOfOtherChequesDepositItemInfo chequesDepositItemInfos;

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
     * Gets the value of the chequesDepositItemInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfOtherChequesDepositItemInfo }
     *     
     */
    public ArrayOfOtherChequesDepositItemInfo getChequesDepositItemInfos() {
        return chequesDepositItemInfos;
    }

    /**
     * Sets the value of the chequesDepositItemInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfOtherChequesDepositItemInfo }
     *     
     */
    public void setChequesDepositItemInfos(ArrayOfOtherChequesDepositItemInfo value) {
        this.chequesDepositItemInfos = value;
    }

}
