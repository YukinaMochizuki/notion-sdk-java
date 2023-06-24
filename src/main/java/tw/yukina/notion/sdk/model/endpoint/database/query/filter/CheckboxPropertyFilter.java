package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class CheckboxPropertyFilter extends DatabasePropertyFilter {

    public static final String CHECKBOX_FIELD = "checkbox";

    @JsonProperty(CHECKBOX_FIELD)
    private CheckboxFilterObject checkboxFilterObject;

    @NotNull
    public static CheckboxPropertyFilter of(String property, CheckboxFilterObject checkboxFilterObject) {
        CheckboxPropertyFilter checkboxPropertyFilter = new CheckboxPropertyFilter();
        checkboxPropertyFilter.setName(property);
        checkboxPropertyFilter.setCheckboxFilterObject(checkboxFilterObject);
        return checkboxPropertyFilter;
    }

    public static CheckboxPropertyFilter equalsOf(String property, boolean equals) {
        return of(property, CheckboxFilterObject.equalsOf(equals));
    }

    public static CheckboxPropertyFilter doesNotEqualOf(String property, boolean doesNotEqual) {
        return of(property, CheckboxFilterObject.doesNotEqualOf(doesNotEqual));
    }
}
