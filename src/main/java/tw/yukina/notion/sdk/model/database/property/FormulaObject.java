package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormulaObject {

    private static final String EXPRESSION_FIELD = "expression";

    @JsonProperty(EXPRESSION_FIELD)
    private String expression;

}
