
package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import ir.carpino.settlement.entity.gateway.pasargad.signatureservice.UserInvoiceRecord;


/**
 * <p>Java class for ArrayOfUserInvoiceRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUserInvoiceRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserInvoiceRecord" type="{http://ibank.fanap.ir/SignatureService}UserInvoiceRecord" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUserInvoiceRecord", propOrder = {
    "userInvoiceRecord"
})
public class ArrayOfUserInvoiceRecord {

    @XmlElement(name = "UserInvoiceRecord", nillable = true)
    protected List<UserInvoiceRecord> userInvoiceRecord;

    /**
     * Gets the value of the userInvoiceRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userInvoiceRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserInvoiceRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserInvoiceRecord }
     * 
     * 
     */
    public List<UserInvoiceRecord> getUserInvoiceRecord() {
        if (userInvoiceRecord == null) {
            userInvoiceRecord = new ArrayList<UserInvoiceRecord>();
        }
        return this.userInvoiceRecord;
    }

}
