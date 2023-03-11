package tw.yukina.notion.sdk.model.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUser {

    private static final String OBJECT_FIELD = "object";

    private static final String ID_FIELD = "id";

    @JsonProperty(OBJECT_FIELD)
    private String object = "user";

    @JsonProperty(ID_FIELD)
    private String id;
}
