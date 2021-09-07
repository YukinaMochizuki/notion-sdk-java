package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.DatabasePropertyDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = DatabasePropertyDeserializer.class)
public class Property {

    private static final String ID_FIELD = "id";
    private static final String TYPE_FIELD = "type";
    private static final String NAME_FIELD = "name";

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(TYPE_FIELD)
    private PropertyType type;

    @JsonProperty(NAME_FIELD)
    private String name;
}
