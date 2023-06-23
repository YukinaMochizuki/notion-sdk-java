package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RelationProperty extends DatabaseProperty {

    private static final String RELATION_FIELD = "relation";

    @JsonProperty(RELATION_FIELD)
    private RelationObject relationObject;

    @NotNull
    public static RelationProperty of(String name, String databaseId) {
        RelationObject relationObject = new RelationObject();
        relationObject.setDatabaseId(databaseId);
        RelationProperty relationProperty = new RelationProperty();
        relationProperty.setName(name);
        relationProperty.setType(PropertyType.RELATION);
        relationProperty.setRelationObject(relationObject);
        return relationProperty;
    }
}
