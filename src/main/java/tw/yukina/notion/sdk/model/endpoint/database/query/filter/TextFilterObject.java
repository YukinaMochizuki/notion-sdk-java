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
public class TextFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_equal";
    public static final String CONTAINS_FIELD = "contains";
    public static final String DOES_NOT_CONTAIN_FIELD = "does_not_contain";
    public static final String STARTS_WITH_FIELD = "starts_with";
    public static final String ENDS_WITH_FIELD = "ends_with";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(EQUALS_FIELD)
    private String equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private String doesNotEqual;

    @JsonProperty(CONTAINS_FIELD)
    private String contains;

    @JsonProperty(DOES_NOT_CONTAIN_FIELD)
    private String doesNotContain;

    @JsonProperty(STARTS_WITH_FIELD)
    private String startsWith;

    @JsonProperty(ENDS_WITH_FIELD)
    private String endsWith;

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static TextFilterObject equalsOf(String equals) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setEquals(equals);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject doesNotEqualOf(String doesNotEqual) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setDoesNotEqual(doesNotEqual);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject containsOf(String contains) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setContains(contains);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject doesNotContainOf(String doesNotContain) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setDoesNotContain(doesNotContain);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject startsWithOf(String startsWith) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setStartsWith(startsWith);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject endsWithOf(String endsWith) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setEndsWith(endsWith);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject isEmptyOf(boolean isEmpty) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setIsEmpty(isEmpty);
        return textFilterObject;
    }

    @NotNull
    public static TextFilterObject isNotEmptyOf(boolean isNotEmpty) {
        TextFilterObject textFilterObject = new TextFilterObject();
        textFilterObject.setIsNotEmpty(isNotEmpty);
        return textFilterObject;
    }
}
