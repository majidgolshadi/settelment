package ir.carpino.settlement.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rides")
@Getter
@Setter
public class Ride {
    @Id
    private String id;

    private String driver;
    private RideInfo rideInfo;
    private String status;

    public String getDriver() {
        String[] tmp = driver.replaceAll("\\{", "")
                .replaceAll("}", "")
                .replaceAll(":", "")
                .replaceAll("\"", "")
                .split(" ");

        if (tmp.length != 7) {
            log.error("driver id not found");
            return "";
        }

        return tmp[6];
    }
}
