package tw.yukina.notion.sdk.model.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.ObjectType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResponseList {
    private static final String OBJECT_FIELD = "object";
    private static final String NEXT_CURSOR_FIELD = "next_cursor";
    private static final String HAS_MORE_FIELD = "has_more";
    private static final String TYPE_FIELD = "type";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.LIST;

    @JsonProperty(NEXT_CURSOR_FIELD)
    private String nextCursor;

    @JsonProperty(HAS_MORE_FIELD)
    private boolean hasMore = false;

    @JsonProperty(TYPE_FIELD)
    private ObjectType type;
}
