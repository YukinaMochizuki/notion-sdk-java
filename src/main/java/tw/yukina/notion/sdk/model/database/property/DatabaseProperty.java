package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.common.Property;
import tw.yukina.notion.sdk.model.deserializer.DatabasePropertyDeserializer;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = DatabasePropertyDeserializer.class)
public class DatabaseProperty extends Property {

    private static final String NAME_FIELD = "name";

    @JsonProperty(NAME_FIELD)
    private String name;

}
