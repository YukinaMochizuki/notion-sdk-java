package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.Equation;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EquationBlock extends Block {

    private static final String EQUATION_FIELD = "equation";

    @JsonProperty(EQUATION_FIELD)
    private Equation equation;

    @NotNull
    public static EquationBlock of(String expression){
        Equation equation = new Equation(expression);
        EquationBlock equationBlock = new EquationBlock();
        equationBlock.setEquation(equation);
        equationBlock.setType(BlockType.EQUATION);
        return equationBlock;
    }
}
