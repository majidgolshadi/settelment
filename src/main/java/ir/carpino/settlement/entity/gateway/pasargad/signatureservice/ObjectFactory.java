
package ir.carpino.settlement.entity.gateway.pasargad.signatureservice;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ir.fanap.ibank.signatureservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ir.fanap.ibank.signatureservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvoiceInput }
     * 
     */
    public InvoiceInput createInvoiceInput() {
        return new InvoiceInput();
    }

    /**
     * Create an instance of {@link ActionResult }
     * 
     */
    public ActionResult createActionResult() {
        return new ActionResult();
    }

    /**
     * Create an instance of {@link ArrayOfUserAmountInfo }
     * 
     */
    public ArrayOfUserAmountInfo createArrayOfUserAmountInfo() {
        return new ArrayOfUserAmountInfo();
    }

    /**
     * Create an instance of {@link UserDepositAmount }
     * 
     */
    public UserDepositAmount createUserDepositAmount() {
        return new UserDepositAmount();
    }

    /**
     * Create an instance of {@link UserAmountInfo }
     * 
     */
    public UserAmountInfo createUserAmountInfo() {
        return new UserAmountInfo();
    }

    /**
     * Create an instance of {@link UserInvoiceRecord }
     * 
     */
    public UserInvoiceRecord createUserInvoiceRecord() {
        return new UserInvoiceRecord();
    }

    /**
     * Create an instance of {@link DepositAmountInput }
     * 
     */
    public DepositAmountInput createDepositAmountInput() {
        return new DepositAmountInput();
    }

}
