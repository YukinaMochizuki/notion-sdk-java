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
public class FormulaFilterObject {

    public static final String TEXT_FIELD = "text";
    public static final String CHECKBOX_FIELD = "checkbox";
    public static final String NUMBER_FIELD = "number";
    public static final String DATE_FIELD = "date";

    @JsonProperty(TEXT_FIELD)
    private TextFilterObject textFilterObject;

    @JsonProperty(CHECKBOX_FIELD)
    private CheckboxFilterObject checkboxFilterObject;

    @JsonProperty(NUMBER_FIELD)
    private NumberFilterObject numberFilterObject;

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static FormulaFilterObject textFilterOf(TextFilterObject textFilterObject) {
        FormulaFilterObject formulaFilterObject = new FormulaFilterObject();
        formulaFilterObject.setTextFilterObject(textFilterObject);
        return formulaFilterObject;
    }

    @NotNull
    public static FormulaFilterObject checkboxFilterOf(CheckboxFilterObject checkboxFilterObject) {
        FormulaFilterObject formulaFilterObject = new FormulaFilterObject();
        formulaFilterObject.setCheckboxFilterObject(checkboxFilterObject);
        return formulaFilterObject;
    }

    @NotNull
    public static FormulaFilterObject numberFilterOf(NumberFilterObject numberFilterObject) {
        FormulaFilterObject formulaFilterObject = new FormulaFilterObject();
        formulaFilterObject.setNumberFilterObject(numberFilterObject);
        return formulaFilterObject;
    }

    @NotNull
    public static FormulaFilterObject dateFilterOf(DateFilterObject dateFilterObject) {
        FormulaFilterObject formulaFilterObject = new FormulaFilterObject();
        formulaFilterObject.setDateFilterObject(dateFilterObject);
        return formulaFilterObject;
    }
}
