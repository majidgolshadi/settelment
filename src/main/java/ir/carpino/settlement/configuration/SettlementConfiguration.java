package ir.carpino.settlement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SettlementConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ir.co.fanap.toranj.ibank.userservices");
        return marshaller;
    }

    @Bean
    public WebServiceTemplate soapConnector(Jaxb2Marshaller marshaller) {
        BlzServiceAdapter client = new BlzServiceAdapter();
        client.setDefaultUri("http://www.thomas-bayer.com/axis2/services/BLZService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
