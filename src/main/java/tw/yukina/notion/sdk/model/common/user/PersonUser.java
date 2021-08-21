package tw.yukina.notion.sdk.model.common.user;

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
    private static final String OBJECT_FIELD = "person";

    private Person person;
}
