package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RelationObject {

    private static final String DATABASE_ID_FIELD = "database_id";
    private static final String SYNCED_PROPERTY_NAME_FIELD = "synced_property_name";
    private static final String SYNCED_PROPERTY_ID_FIELD = "synced_property_id";

    @JsonProperty(DATABASE_ID_FIELD)
    private String databaseId;

    @Nullable
    @JsonProperty(SYNCED_PROPERTY_NAME_FIELD)
    private String syncedPropertyName;

    @Nullable
    @JsonProperty(SYNCED_PROPERTY_ID_FIELD)
    private String syncedPropertyId;

}
