package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_equal";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(EQUALS_FIELD)
    private String equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private String doesNotEqual;

    @JsonProperty(IS_EMPTY_FIELD)
    private boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private boolean isNotEmpty;
}
