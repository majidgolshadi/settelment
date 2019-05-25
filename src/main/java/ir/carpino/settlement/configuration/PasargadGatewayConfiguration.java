package ir.carpino.settlement.configuration;

import ir.carpino.settlement.gateway.PasargadGateway;
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
    public long delayBetweenRequests;

    private final String soapUri = "https://ib.bpi.ir/WebServices/UserServices.asmx";
    private final String soapUriWsdl = "https://ib.bpi.ir/WebServices/UserServices.asmx?wsdl";

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ir.carpino.settlement.entity.gateway.pasargad.userservices");
        return marshaller;
    }

    @Bean
    public PasargadGateway soapConnector(Jaxb2Marshaller marshaller) {
        PasargadGateway client = new PasargadGateway();
        client.setDefaultUri(soapUri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
