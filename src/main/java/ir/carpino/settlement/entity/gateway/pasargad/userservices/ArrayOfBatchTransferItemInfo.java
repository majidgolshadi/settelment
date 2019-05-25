
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBatchTransferItemInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBatchTransferItemInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BatchTransferItemInfo" type="{http://ibank.toranj.fanap.co.ir/UserServices}BatchTransferItemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBatchTransferItemInfo", propOrder = {
    "batchTransferItemInfo"
})
public class ArrayOfBatchTransferItemInfo {

    @XmlElement(name = "BatchTransferItemInfo", nillable = true)
    protected List<BatchTransferItemInfo> batchTransferItemInfo;

    /**
     * Gets the value of the batchTransferItemInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the batchTransferItemInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBatchTransferItemInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BatchTransferItemInfo }
     * 
     * 
     */
    public List<BatchTransferItemInfo> getBatchTransferItemInfo() {
        if (batchTransferItemInfo == null) {
            batchTransferItemInfo = new ArrayList<BatchTransferItemInfo>();
        }
        return this.batchTransferItemInfo;
    }

}
