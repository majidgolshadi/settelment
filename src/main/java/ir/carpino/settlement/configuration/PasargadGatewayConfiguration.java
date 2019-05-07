package ir.carpino.settlement.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PasargadGatewayConfiguration {
    public String username;
    public String privateKeyPath;
    public String sourceDeposit;
    public int maxTransactionPerBatch;
    public String maxWaitTime;
}
