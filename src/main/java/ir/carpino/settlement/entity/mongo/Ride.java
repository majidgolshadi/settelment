package ir.carpino.settlement.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    @DBRef
    private Driver driver;
    private RideInfo rideInfo;
    private String status;
}
