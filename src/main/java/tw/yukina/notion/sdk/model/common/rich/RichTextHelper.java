package tw.yukina.notion.sdk.model.common.rich;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.mention.*;
import tw.yukina.notion.sdk.model.common.unit.Database;
import tw.yukina.notion.sdk.model.common.unit.Page;
import tw.yukina.notion.sdk.model.common.user.Person;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.UserType;
import tw.yukina.notion.sdk.model.deserializer.DateTimeDeserializer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public static List<RichText> createDefaultArrayText(String content){
        return new ArrayList<>(Collections.singleton(createDefaultText(content)));
    }

    @NotNull
    public static MentionText createPageMention(String title, String uuid){
        PageMention pageMention = new PageMention();
        pageMention.setPage(Page.builder().pageId(uuid).build());
        pageMention.setMentionType(MentionType.PAGE);

        return createDefaultMentionText(title, pageMention);
    }

    @NotNull
    public static MentionText createDatabaseMention(String title, String uuid){
        DatabaseMention databaseMention = new DatabaseMention();
        databaseMention.setDatabase(Database.builder().databaseId(uuid).build());
        databaseMention.setMentionType(MentionType.DATABASE);

        return createDefaultMentionText(title, databaseMention);
    }

    @NotNull
    public static MentionText createDateMention(String start) throws Exception {

        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(DateTimeDeserializer.parse(start)
                .orElseThrow(() -> new Exception("The date " + start + " does not match any available formats")));

        return createDefaultMentionText(start + " → ", dateMention);
    }

    @NotNull
    public static MentionText createDateMention(String start, String end) throws Exception {

        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(DateTimeDeserializer.parse(start, end)
                .orElseThrow(() -> new Exception("The date " + start + " does not match any available formats")));

        return createDefaultMentionText(start + " → " + end, dateMention);
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

        return createDefaultMentionText("@" + name, userMention);
    }

    @NotNull
    public static EquationText createEquationText(String expression){
        Equation equation = new Equation();
        equation.setExpression(expression);

        EquationText equationText = new EquationText();
        equationText.setEquation(equation);
        equationText.setAnnotations(createDefaultAnnotation());
        equationText.setPlainText(expression);
        equationText.setType(TextType.EQUATION);
        return equationText;
    }

    @NotNull
    public static MentionText createDefaultMentionText(String plainText, Mention mention){
        MentionText mentionText = new MentionText();
        mentionText.setMention(mention);
        mentionText.setAnnotations(createDefaultAnnotation());
        mentionText.setPlainText(plainText);
        mentionText.setType(TextType.MENTION);
        return mentionText;
    }

    @NotNull
    public static Annotation createDefaultAnnotation(){
        Annotation annotation = new Annotation();
        annotation.setAllDefault();
        return annotation;
    }

    public static void setLinkToText(@NotNull Text text, URL url){
        Link link = new Link();
        link.setUrl(url);

        text.setHref(url);
        text.getTextObject().setLink(link);
    }
}
