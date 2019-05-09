package ir.carpino.settlement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;


@Configuration
@Getter
@Setter
public class PasargadGatewayConfiguration {
    public String username;
    public String privateKeyPath;
    public String sourceDeposit;
    public int maxTransactionPerBatch;
    public String maxWaitTime;
}
