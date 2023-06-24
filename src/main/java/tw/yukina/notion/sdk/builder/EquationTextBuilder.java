package tw.yukina.notion.sdk.builder;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.Equation;
import tw.yukina.notion.sdk.model.common.rich.EquationText;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.TextType;

public class EquationTextBuilder extends RichTextBuilder<EquationText> {

    private String expression = "";

    @NotNull
    public static EquationTextBuilder of(String expression) {
        EquationTextBuilder equationTextBuilder = new EquationTextBuilder();
        equationTextBuilder.setExpression(expression);
        return equationTextBuilder;
    }

    public EquationTextBuilder setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    @Override
    public RichText buildSelf() {
        Equation equation = new Equation();
        equation.setExpression(expression);

        EquationText equationText = new EquationText();
        equationText.setType(TextType.EQUATION);
        equationText.setEquation(equation);
        equationText.setPlainText(expression);

        setRichTextStyle(equationText);
        return equationText;
    }
}
