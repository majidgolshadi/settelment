package ir.carpino.settlement.entity.gateway.pasargad.userservices;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public CoreBatchTransferPaya createCoreBatchTransferPaya() {
        return new CoreBatchTransferPaya();
    }

    public CoreBatchTransferPayaResponse createCoreBatchTransferPayaResponse() {
        return new CoreBatchTransferPayaResponse();
    }
}