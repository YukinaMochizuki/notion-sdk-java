package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class QuoteBlock extends Block implements TextBlock {
    private static final String PARAGRAPH_FIELD = "quote";

    @JsonProperty(PARAGRAPH_FIELD)
    private Paragraph paragraph;

    @NotNull
    public static QuoteBlock of(List<RichText> richTexts){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        QuoteBlock quoteBlock = new QuoteBlock();
        quoteBlock.setParagraph(paragraph);
        quoteBlock.setType(BlockType.PARAGRAPH);
        return quoteBlock;
    }
}
