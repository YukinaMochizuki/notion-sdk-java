package tw.yukina.notion.sdk.model.common.rich;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.mention.MentionType;
import tw.yukina.notion.sdk.model.common.rich.mention.PageMention;
import tw.yukina.notion.sdk.model.common.rich.mention.UserMention;
import tw.yukina.notion.sdk.model.common.unit.Page;
import tw.yukina.notion.sdk.model.common.user.Person;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.UserType;

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
    public static MentionText createPageMention(String title, String uuid){
        PageMention pageMention = new PageMention();
        pageMention.setPage(Page.builder().pageId(uuid).build());
        pageMention.setMentionType(MentionType.PAGE);

        MentionText mentionText = new MentionText();
        mentionText.setMention(pageMention);
        mentionText.setAnnotations(createDefaultAnnotation());
        mentionText.setPlainText(title);
        mentionText.setType(TextType.MENTION);

        return mentionText;
    }

    @NotNull
    public static MentionText createPersonMention(String name, String uuid){
        PersonUser personUser = new PersonUser();
        personUser.setPerson(new Person());
        personUser.setId(uuid);
        personUser.setName(name);
        personUser.setUserType(UserType.PERSON);

        UserMention userMention = new UserMention();
        userMention.setUser(personUser);
        userMention.setMentionType(MentionType.USER);

        MentionText mentionText = new MentionText();
        mentionText.setMention(userMention);
        mentionText.setAnnotations(createDefaultAnnotation());
        mentionText.setPlainText("@" + name);
        mentionText.setType(TextType.MENTION);

        return mentionText;
    }

    @NotNull
    public static Annotation createDefaultAnnotation(){
        Annotation annotation = new Annotation();
        annotation.setAllDefault();
        return annotation;
    }
}
