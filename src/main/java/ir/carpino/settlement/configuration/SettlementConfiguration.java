package ir.carpino.settlement.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SettlementConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ir.co.fanap.toranj.ibank.userservices");
        marshaller.setContextPath("ir.fanap.ibank.signatureservice");
        return marshaller;
    }
}
