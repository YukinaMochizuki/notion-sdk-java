package tw.yukina.notion.sdk.model.common.rich;

import org.jetbrains.annotations.NotNull;

public class RichTextHelper {

    @NotNull
    public static Text createDefaultText(String content){
        TextObject textObject = new TextObject();
        textObject.setContent(content);

        Text text = new Text();
        text.setTextObject(textObject);
        text.setAnnotations(createDefaultAnnotation());
        text.setPlainText(content);
        text.setType(TextType.TEXT);

        return text;
    }

    @NotNull
    public static Annotation createDefaultAnnotation(){
        Annotation annotation = new Annotation();
        annotation.setAllDefault();
        return annotation;
    }
}
