package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RollupObject {

    private static final String RELATION_PROPERTY_NAME_FIELD = "relation_property_name";
    private static final String RELATION_PROPERTY_ID_FIELD = "relation_property_id";
    private static final String ROLLUP_PROPERTY_NAME_FIELD = "rollup_property_name";
    private static final String ROLLUP_PROPERTY_ID_FIELD = "rollup_property_id";
    private static final String FUNCTION_FIELD = "function";

    @JsonProperty(RELATION_PROPERTY_NAME_FIELD)
    private String relationPropertyName;

    @JsonProperty(RELATION_PROPERTY_ID_FIELD)
    private String relationPropertyId;

    @JsonProperty(ROLLUP_PROPERTY_NAME_FIELD)
    private String rollupPropertyName;

    @JsonProperty(ROLLUP_PROPERTY_ID_FIELD)
    private String rollupPropertyId;

    @JsonProperty(FUNCTION_FIELD)
    private RollupFunctionType rollupFunctionType;

}
