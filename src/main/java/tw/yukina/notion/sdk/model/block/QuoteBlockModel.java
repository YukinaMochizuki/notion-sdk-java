package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class QuoteBlockModel extends BlockModel implements TextBlock {
    private static final String PARAGRAPH_FIELD = "quote";

    @JsonProperty(PARAGRAPH_FIELD)
    private Paragraph paragraph;

    @Override
    public boolean canHaveChildren() {
        return true;
    }

    @NotNull
    public static QuoteBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static QuoteBlockModel of(List<RichText> richTexts) {
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        QuoteBlockModel quoteBlock = new QuoteBlockModel();
        quoteBlock.setParagraph(paragraph);
        quoteBlock.setType(BlockType.QUOTE);
        return quoteBlock;
    }
}
