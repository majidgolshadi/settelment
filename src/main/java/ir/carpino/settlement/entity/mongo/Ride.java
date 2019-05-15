package ir.carpino.settlement.entity.mongo;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rides")
public class Ride {
    @Id
    public String id;

    public String status;
    public Driver driver;
    public RideInfo rideInfo;
}
