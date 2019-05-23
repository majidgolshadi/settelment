package ir.carpino.settlement.entity.mongo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DriverObjectId {
    @Id
    private String id;
}
