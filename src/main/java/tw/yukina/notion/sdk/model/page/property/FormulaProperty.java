package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class FormulaProperty extends PageProperty {

    private static final String FORMULA_FIELD = "formula";

    @JsonProperty(FORMULA_FIELD)
    private FormulaObject formula;

}
