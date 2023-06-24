package tw.yukina.notion.sdk.builder;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.MentionText;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.TextType;
import tw.yukina.notion.sdk.model.common.rich.mention.Mention;

public class MentionTextBuilder extends RichTextBuilder<MentionText> {

    private Mention mention;

    @NotNull
    public static MentionTextBuilder of(@NotNull Mention mention) {
        MentionTextBuilder mentionTextBuilder = new MentionTextBuilder();
        mentionTextBuilder.mention = mention;
        return mentionTextBuilder;
    }

    @Override
    public RichText buildSelf() {
        if (this.mention == null) throw new RuntimeException("Mention can not be null");

        MentionText mentionText = new MentionText();
        mentionText.setType(TextType.MENTION);
        mentionText.setMention(mention);
        setRichTextStyle(mentionText);

        return mentionText;
    }
}
