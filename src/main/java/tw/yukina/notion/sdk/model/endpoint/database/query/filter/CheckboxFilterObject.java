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
public class CheckboxFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_equal";

    @JsonProperty(EQUALS_FIELD)
    private Boolean equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private Boolean doesNotEqual;

    @NotNull
    public static CheckboxFilterObject equalsOf(boolean equals){
        CheckboxFilterObject checkboxFilterObject = new CheckboxFilterObject();
        checkboxFilterObject.setEquals(equals);
        return checkboxFilterObject;
    }

    @NotNull
    public static CheckboxFilterObject doesNotEqualOf(boolean doesNotEqual){
        CheckboxFilterObject checkboxFilterObject = new CheckboxFilterObject();
        checkboxFilterObject.setDoesNotEqual(doesNotEqual);
        return checkboxFilterObject;
    }
}
