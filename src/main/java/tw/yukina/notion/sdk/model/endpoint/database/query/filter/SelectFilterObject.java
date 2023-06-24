package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static SelectFilterObject equalsOf(String equals) {
        SelectFilterObject selectFilterObject = new SelectFilterObject();
        selectFilterObject.setEquals(equals);
        return selectFilterObject;
    }

    @NotNull
    public static SelectFilterObject doesNotEqualOf(String doesNotEqual) {
        SelectFilterObject selectFilterObject = new SelectFilterObject();
        selectFilterObject.setDoesNotEqual(doesNotEqual);
        return selectFilterObject;
    }

    @NotNull
    public static SelectFilterObject isEmptyOf(boolean isEmpty) {
        SelectFilterObject selectFilterObject = new SelectFilterObject();
        selectFilterObject.setIsEmpty(isEmpty);
        return selectFilterObject;
    }

    @NotNull
    public static SelectFilterObject isNotEmptyOf(boolean isNotEmpty) {
        SelectFilterObject selectFilterObject = new SelectFilterObject();
        selectFilterObject.setIsNotEmpty(isNotEmpty);
        return selectFilterObject;
    }
}
