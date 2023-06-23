package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.common.query.DirectionEnum;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseSort {

    public static final String PROPERTY_FIELD = "property";
    public static final String TIMESTAMP_FIELD = "timestamp";
    public static final String DIRECTION_FIELD = "direction";

    @JsonProperty(PROPERTY_FIELD)
    private String property;

    @JsonProperty(TIMESTAMP_FIELD)
    private SortTimestamp sortTimestamp;

    @JsonProperty(DIRECTION_FIELD)
    private DirectionEnum directionEnum;

}
