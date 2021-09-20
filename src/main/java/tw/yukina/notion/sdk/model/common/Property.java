package tw.yukina.notion.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class Property {

    public static final String ID_FIELD = "id";
    public static final String TYPE_FIELD = "type";

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(TYPE_FIELD)
    private PropertyType type;

}
