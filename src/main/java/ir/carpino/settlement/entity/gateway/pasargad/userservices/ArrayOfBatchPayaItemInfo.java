
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBatchPayaItemInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBatchPayaItemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BatchPayaItemInfo" type="{http://ibank.toranj.fanap.co.ir/UserServices}BatchPayaItemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBatchPayaItemInfo", propOrder = {
    "batchPayaItemInfo"
})
public class ArrayOfBatchPayaItemInfo {

    @XmlElement(name = "BatchPayaItemInfo", nillable = true)
    protected List<BatchPayaItemInfo> batchPayaItemInfo;

    /**
     * Gets the value of the batchPayaItemInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchPayaItemInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchPayaItemInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchPayaItemInfo }
     * 
     * 
     */
    public List<BatchPayaItemInfo> getBatchPayaItemInfo() {
        if (batchPayaItemInfo == null) {
            batchPayaItemInfo = new ArrayList<BatchPayaItemInfo>();
        }
        return this.batchPayaItemInfo;
    }

}
