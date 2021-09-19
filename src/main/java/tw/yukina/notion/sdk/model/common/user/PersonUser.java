package tw.yukina.notion.sdk.model.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PersonUser extends User {

    private static final String PERSON_FIELD = "person";

    @JsonProperty(PERSON_FIELD)
    private Person person;

}
