package tw.yukina.notion.sdk.builder;

import tw.yukina.notion.sdk.model.common.rich.Equation;
import tw.yukina.notion.sdk.model.common.rich.EquationText;
import tw.yukina.notion.sdk.model.common.rich.TextType;

public class EquationTextBuilder extends RichTextBuilder<EquationText> {

    private String expression = "";

    public EquationTextBuilder setExpression(String expression){
        this.expression = expression;
        return this;
    }

    @Override
    public EquationText build() {
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
