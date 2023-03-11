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
public class DualPropertyObject {
    private static final String SYNCED_PROPERTY_NAME_FIELD = "synced_property_name";
    private static final String SYNCED_PROPERTY_ID_FIELD = "synced_property_id";

    @Nullable
    @JsonProperty(SYNCED_PROPERTY_NAME_FIELD)
    private String syncedPropertyName;

    @Nullable
    @JsonProperty(SYNCED_PROPERTY_ID_FIELD)
    private String syncedPropertyId;
}
