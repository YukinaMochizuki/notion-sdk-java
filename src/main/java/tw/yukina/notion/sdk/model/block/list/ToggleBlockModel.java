package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.TextBlock;
import tw.yukina.notion.sdk.model.block.Paragraph;
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
public class ToggleBlockModel extends BlockModel implements TextBlock {
    private static final String TOGGLE_FIELD = "toggle";

    @JsonProperty(TOGGLE_FIELD)
    private Paragraph paragraph;

    @Override
    public boolean canHaveChildren() {
        return true;
    }

    @NotNull
    public static List<ToggleBlockModel> of(String[] plainTexts) {
        List<ToggleBlockModel> toggleBlockModels = new ArrayList<>();
        for(String plainText: plainTexts){
            toggleBlockModels.add(of(plainText));
        }
        return toggleBlockModels;
    }

    @NotNull
    public static ToggleBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static ToggleBlockModel of(List<RichText> richTexts) {
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        paragraph.setColor(TextColor.DEFAULT);
        ToggleBlockModel toggleBlock = new ToggleBlockModel();
        toggleBlock.setParagraph(paragraph);
        toggleBlock.setType(BlockType.TOGGLE);
        return toggleBlock;
    }
}
