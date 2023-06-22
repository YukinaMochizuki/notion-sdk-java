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
public class MultiSelectFilterObject {

    public static final String CONTAINS_FIELD = "contains";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_contain";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(CONTAINS_FIELD)
    private String contains;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private String doesNotContains;

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static MultiSelectFilterObject containsOf(String equals){
        MultiSelectFilterObject selectFilterObject = new MultiSelectFilterObject();
        selectFilterObject.setContains(equals);
        return selectFilterObject;
    }

    @NotNull
    public static MultiSelectFilterObject doesNotContainsOf(String doesNotEqual){
        MultiSelectFilterObject selectFilterObject = new MultiSelectFilterObject();
        selectFilterObject.setDoesNotContains(doesNotEqual);
        return selectFilterObject;
    }

    @NotNull
    public static MultiSelectFilterObject isEmptyOf(boolean isEmpty){
        MultiSelectFilterObject selectFilterObject = new MultiSelectFilterObject();
        selectFilterObject.setIsEmpty(isEmpty);
        return selectFilterObject;
    }

    @NotNull
    public static MultiSelectFilterObject isNotEmptyOf(boolean isNotEmpty){
        MultiSelectFilterObject selectFilterObject = new MultiSelectFilterObject();
        selectFilterObject.setIsNotEmpty(isNotEmpty);
        return selectFilterObject;
    }
}
