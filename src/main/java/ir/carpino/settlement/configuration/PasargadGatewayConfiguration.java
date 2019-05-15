package ir.carpino.settlement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(
        prefix = "gateway.pasargad"
)
public class PasargadGatewayConfiguration {
    public String username;
    public String privateKeyPath;
    public String sourceDeposit;
    public int maxTransactionPerBatch;
    public String maxWaitTime;
    public int delayBetweenRequests;
}
