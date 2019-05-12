package ir.carpino.settlement.configuration;

import ir.carpino.settlement.gateway.PasargadGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SettlementConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ir.co.fanap.toranj.ibank.userservices");
        return marshaller;
    }

    @Bean
    public PasargadGateway soapConnector(Jaxb2Marshaller marshaller) {
        PasargadGateway client = new PasargadGateway();
        client.setDefaultUri("https://ib.bpi.ir/WebServices/UserServices.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
