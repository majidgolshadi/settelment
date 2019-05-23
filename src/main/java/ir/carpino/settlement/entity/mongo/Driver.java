package ir.carpino.settlement.entity.mongo;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "drivers")
@Getter
@Setter
public class Driver {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private BankAccountInfo bankAccountInfo;
}
