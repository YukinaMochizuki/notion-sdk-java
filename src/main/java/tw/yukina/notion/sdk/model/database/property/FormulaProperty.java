package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class FormulaProperty extends DatabaseProperty {

    private static final String FORMULA_FIELD = "formula";

    @JsonProperty(FORMULA_FIELD)
    private FormulaObject formulaObject;

    @NotNull
    public static FormulaProperty of(String name, String expression){
        FormulaObject formulaObject = new FormulaObject();
        formulaObject.setExpression(expression);
        FormulaProperty formulaProperty = new FormulaProperty();
        formulaProperty.setName(name);
        formulaProperty.setType(PropertyType.FORMULA);
        formulaProperty.setFormulaObject(formulaObject);
        return formulaProperty;
    }
}
