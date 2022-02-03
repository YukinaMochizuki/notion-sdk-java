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
    private Double equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private Double doesNotEqual;

    @JsonProperty(GREATER_THAN_FIELD)
    private Double contains;

    @JsonProperty(LESS_THAN_FIELD)
    private Double doesNotContain;

    @JsonProperty(GREATER_THEN_OR_EQUAL_TO_FIELD)
    private Double startsWith;

    @JsonProperty(LESS_THAN_OR_EQUAL_TO_FIELD)
    private Double endsWith;

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static NumberFilterObject equalsOf(Double equals){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setEquals(equals);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject doesNotEqualOf(Double doesNotEqual){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setDoesNotEqual(doesNotEqual);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject containsOf(Double contains){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setContains(contains);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject doesNotContainOf(Double doesNotContain){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setDoesNotContain(doesNotContain);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject startsWithOf(Double startsWith){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setStartsWith(startsWith);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject endsWithOf(Double endsWith){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setEndsWith(endsWith);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject isEmptyOf(boolean isEmpty){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setIsEmpty(isEmpty);
        return numberFilterObject;
    }

    @NotNull
    public static NumberFilterObject isNotEmptyOf(boolean isNotEmpty){
        NumberFilterObject numberFilterObject = new NumberFilterObject();
        numberFilterObject.setIsNotEmpty(isNotEmpty);
        return numberFilterObject;
    }
}
