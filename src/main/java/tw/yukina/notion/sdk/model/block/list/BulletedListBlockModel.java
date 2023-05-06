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
public class BulletedListBlockModel extends BlockModel implements TextBlock {
    private static final String BULLETED_LIST_FIELD = "bulleted_list_item";

    @JsonProperty(BULLETED_LIST_FIELD)
    private Paragraph paragraph;

    @Override
    public boolean canHaveChildren() {
        return true;
    }

    @NotNull
    public static List<BulletedListBlockModel> of(String ...plainTexts) {
        List<BulletedListBlockModel> bulletedListBlockModels = new ArrayList<>();
        for(String plainText: plainTexts){
            bulletedListBlockModels.add(of(plainText));
        }
        return bulletedListBlockModels;
    }

    @NotNull
    public static BulletedListBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static BulletedListBlockModel of(List<RichText> richTexts) {
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);
        paragraph.setColor(TextColor.DEFAULT);
        BulletedListBlockModel bulletedListBlock = new BulletedListBlockModel();
        bulletedListBlock.setParagraph(paragraph);
        bulletedListBlock.setType(BlockType.BULLETED_LIST_ITEM);
        return bulletedListBlock;
    }
}
