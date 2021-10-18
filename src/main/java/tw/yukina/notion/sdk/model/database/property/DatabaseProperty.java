package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.Property;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.deserializer.DatabasePropertyDeserializer;
import tw.yukina.notion.sdk.model.serializer.DatabasePropertySerializer;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = DatabasePropertySerializer.class)
@JsonDeserialize(using = DatabasePropertyDeserializer.class)
public class DatabaseProperty extends Property {

    public static final String NAME_FIELD = "name";

    @JsonProperty(NAME_FIELD)
    private String name;

    @NotNull
    public static DatabaseProperty of(String name, PropertyType propertyType){
        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setName(name);
        databaseProperty.setType(propertyType);

        return databaseProperty;
    }
}
