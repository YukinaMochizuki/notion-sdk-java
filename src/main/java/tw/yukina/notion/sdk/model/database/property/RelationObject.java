package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.deserializer.DatabaseRelationDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = DatabaseRelationDeserializer.class)
public class RelationObject {

    private static final String DATABASE_ID_FIELD = "database_id";

    private static final String TYPE_FIELD = "type";

    @JsonProperty(DATABASE_ID_FIELD)
    private String databaseId;

    @JsonProperty(TYPE_FIELD)
    private RelationPropertyType type;
}
