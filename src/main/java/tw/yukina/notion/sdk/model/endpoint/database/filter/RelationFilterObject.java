package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RelationFilterObject {

    public static final String CONTAINS_FIELD = "contains";
    public static final String DOES_NOT_CONTAINS_FIELD = "does_not_contain";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(CONTAINS_FIELD)
    private String containsUuid;

    @JsonProperty(DOES_NOT_CONTAINS_FIELD)
    private String doesNotContainsUuid;

    @JsonProperty(IS_EMPTY_FIELD)
    private boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private boolean isNotEmpty;
}
