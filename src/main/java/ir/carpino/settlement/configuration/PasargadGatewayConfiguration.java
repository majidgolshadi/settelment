package ir.carpino.settlement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Getter
@Setter
@Configuration
@ConfigurationProperties(
        prefix = "settlement.gateway.pasargad"
)
public class PasargadGatewayConfiguration {
    public String username;
    public String privateKeyPath;
    public String sourceDeposit;
    public int maxTransactionPerBatch;
    public String maxWaitTime;
    public int delayBetweenRequests;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ir.co.fanap.toranj.ibank.userservices");
        marshaller.setContextPath("ir.fanap.ibank.signatureservice");
        return marshaller;
    }
}
