
package ir.carpino.settlement.entity.gateway.pasargad.signatureservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfUserAmountInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUserAmountInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserAmountInfo" type="{http://ibank.fanap.ir/SignatureService}UserAmountInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUserAmountInfo", propOrder = {
    "userAmountInfo"
})
public class ArrayOfUserAmountInfo {

    @XmlElement(name = "UserAmountInfo", nillable = true)
    protected List<UserAmountInfo> userAmountInfo;

    /**
     * Gets the value of the userAmountInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAmountInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAmountInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserAmountInfo }
     * 
     * 
     */
    public List<UserAmountInfo> getUserAmountInfo() {
        if (userAmountInfo == null) {
            userAmountInfo = new ArrayList<UserAmountInfo>();
        }
        return this.userAmountInfo;
    }

}
