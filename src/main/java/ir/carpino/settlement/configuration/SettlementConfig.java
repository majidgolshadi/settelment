package ir.carpino.settlement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(
        prefix = "settlement"
)
public class SettlementConfig {
    private String skipSettleForBank;
    private long minChargeToPay;
    private long maxChargeToPay;
    private boolean testMode;
    private boolean recheckSettle;
}
