package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class NumberedListBlockModel extends BlockModel implements TextBlock {
    private static final String NUMBERED_LIST_FIELD = "numbered_list_item";

    @JsonProperty(NUMBERED_LIST_FIELD)
    private Paragraph paragraph;

    @Override
    public boolean canHaveChildren() {
        return true;
    }

    @NotNull
    public static List<NumberedListBlockModel> of(String ...plainTexts) {
        List<NumberedListBlockModel> numberedListBlockModels = new ArrayList<>();
        for(String plainText: plainTexts){
            numberedListBlockModels.add(of(plainText));
        }
        return numberedListBlockModels;
    }

    @NotNull
    public static NumberedListBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static NumberedListBlockModel of(List<RichText> richTexts) {
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        paragraph.setColor(TextColor.DEFAULT);
        NumberedListBlockModel numberedListBlock = new NumberedListBlockModel();
        numberedListBlock.setParagraph(paragraph);
        numberedListBlock.setType(BlockType.NUMBERED_LIST_ITEM);
        return numberedListBlock;
    }
}
