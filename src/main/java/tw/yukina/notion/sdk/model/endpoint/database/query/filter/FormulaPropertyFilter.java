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
public class FormulaPropertyFilter extends DatabasePropertyFilter {

    public static final String FORMULA_FIELD = "formula";

    @JsonProperty(FORMULA_FIELD)
    private FormulaFilterObject formulaFilterObject;

    @NotNull
    public static FormulaPropertyFilter of(String property, FormulaFilterObject formulaFilterObject){
        FormulaPropertyFilter formulaPropertyFilter = new FormulaPropertyFilter();
        formulaPropertyFilter.setName(property);
        formulaPropertyFilter.setFormulaFilterObject(formulaFilterObject);
        return formulaPropertyFilter;
    }
}
