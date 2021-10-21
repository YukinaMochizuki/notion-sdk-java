package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NumberFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_equal";
    public static final String GREATER_THAN_FIELD = "greater_than";
    public static final String LESS_THAN_FIELD = "less_than";
    public static final String GREATER_THEN_OR_EQUAL_TO_FIELD = "greater_than_or_equal_to";
    public static final String LESS_THAN_OR_EQUAL_TO_FIELD = "less_than_or_equal_to";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(EQUALS_FIELD)
    private double equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private double doesNotEqual;

    @JsonProperty(GREATER_THAN_FIELD)
    private double contains;

    @JsonProperty(LESS_THAN_FIELD)
    private double doesNotContain;

    @JsonProperty(GREATER_THEN_OR_EQUAL_TO_FIELD)
    private double startsWith;

    @JsonProperty(LESS_THAN_OR_EQUAL_TO_FIELD)
    private double endsWith;

    @JsonProperty(IS_EMPTY_FIELD)
    private boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private boolean isNotEmpty;
}
