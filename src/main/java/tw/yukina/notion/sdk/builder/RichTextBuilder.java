package tw.yukina.notion.sdk.builder;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.common.rich.Annotation;
import tw.yukina.notion.sdk.model.common.rich.RichText;

public abstract class RichTextBuilder<T extends RichText> {

    private boolean isBold = false;

    private boolean isItalic = false;

    private boolean isStrikethrough = false;

    private boolean isUnderline = false;

    private boolean isCode = false;

    private TextColor color = TextColor.DEFAULT;

    public RichTextBuilder<T> setBold(){
        this.isBold = true;
        return this;
    }

    public RichTextBuilder<T> setItalic(){
        this.isItalic = true;
        return this;
    }

    public RichTextBuilder<T> setStrolethrough(){
        this.isStrikethrough = true;
        return this;
    }

    public RichTextBuilder<T> setUnderline(){
        this.isUnderline = true;
        return this;
    }

    public RichTextBuilder<T> setCodeStyle(){
        this.isCode = true;
        return this;
    }

    public RichTextBuilder<T> setColor(@NotNull TextColor color){
        this.color = color;
        return this;
    }

    public abstract T build();

    protected void setRichTextStyle(@NotNull T richText){
        Annotation annotation = new Annotation();
        annotation.setBold(this.isBold);
        annotation.setItalic(this.isItalic);
        annotation.setStrikethrough(this.isStrikethrough);
        annotation.setUnderline(this.isUnderline);
        annotation.setCode(this.isCode);
        annotation.setColor(this.color);
        richText.setAnnotations(annotation);
    }
}
