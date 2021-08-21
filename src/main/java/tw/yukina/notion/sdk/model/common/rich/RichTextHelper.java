package tw.yukina.notion.sdk.model.common.rich;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.mention.MentionType;
import tw.yukina.notion.sdk.model.common.rich.mention.PageMention;
import tw.yukina.notion.sdk.model.common.unit.Page;

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

    public static MentionText createPageMention(String uuid){
        PageMention pageMention = new PageMention();
        pageMention.setPage(Page.builder().pageId(uuid).build());
        pageMention.setMentionType(MentionType.PAGE);

        MentionText mentionText = new MentionText();
        mentionText.setMention(pageMention);
        mentionText.setAnnotations(createDefaultAnnotation());

        return mentionText;
    }

    @NotNull
    public static Annotation createDefaultAnnotation(){
        Annotation annotation = new Annotation();
        annotation.setAllDefault();
        return annotation;
    }
}
