package tw.yukina.notion.sdk.model.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.net.URL;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User {
    private static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String TYPE_FIELD = "type";
    private static final String NAME_FIELD = "name";
    private static final String AVATAR_URL_FIELD = "avatar_url";

    @JsonProperty(OBJECT_FIELD)
    private String object;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(TYPE_FIELD)
    private UserType userType;

    @JsonProperty(NAME_FIELD)
    private String name;

    @JsonProperty(AVATAR_URL_FIELD)
    private URL avatar;
}
