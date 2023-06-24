package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ParagraphBlockModel extends BlockModel implements TextBlock {
    private static final String PARAGRAPH_FIELD = "paragraph";

    @JsonProperty(PARAGRAPH_FIELD)
    private Paragraph paragraph;

    @NotNull
    public static ParagraphBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static ParagraphBlockModel of(List<RichText> richTexts) {
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        paragraph.setColor(TextColor.DEFAULT);
        ParagraphBlockModel paragraphBlock = new ParagraphBlockModel();
        paragraphBlock.setParagraph(paragraph);
        paragraphBlock.setType(BlockType.PARAGRAPH);
        return paragraphBlock;
    }

    public ParagraphBlockModel setText(List<RichText> richTexts) {
        if (paragraph == null) paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        return this;
    }

    public ParagraphBlockModel setColor(TextColor color) {
        if (paragraph == null) paragraph = new Paragraph();
        paragraph.setColor(color);
        return this;
    }

    public ParagraphBlockModel setChildren(List<BlockModel> children) {
        if (paragraph == null) paragraph = new Paragraph();
        paragraph.setChildren(children);
        return this;
    }

    @Override
    public boolean canHaveChildren() {
        return true;
    }
}
