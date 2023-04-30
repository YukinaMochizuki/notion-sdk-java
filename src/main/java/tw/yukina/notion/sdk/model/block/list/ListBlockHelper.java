package tw.yukina.notion.sdk.model.block.list;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.Paragraph;
import tw.yukina.notion.sdk.model.block.TextBlock;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Deprecated
public class ListBlockHelper {

    @NotNull
    public static BulletedListBlockModel createDefaultBulletedList(List<RichText> texts){
        BulletedListBlockModel bulletedListBlock = new BulletedListBlockModel();
        initDefaultListBlock(texts, bulletedListBlock);
        bulletedListBlock.setType(BlockType.BULLETED_LIST_ITEM);

        return bulletedListBlock;
    }

    @NotNull
    public static NumberedListBlockModel createDefaultNumberedList(List<RichText> texts){
        NumberedListBlockModel numberedListBlock = new NumberedListBlockModel();
        initDefaultListBlock(texts, numberedListBlock);
        numberedListBlock.setType(BlockType.NUMBERED_LIST_ITEM);

        return numberedListBlock;
    }

    @NotNull
    public static ToggleBlockModel createDefaultToggle(List<RichText> texts){
        ToggleBlockModel toggleBlock = new ToggleBlockModel();
        initDefaultListBlock(texts, toggleBlock);
        toggleBlock.setType(BlockType.TOGGLE);

        return toggleBlock;
    }

    public static <T extends TextBlock> void initDefaultListBlock(List<RichText> texts, T t){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(texts);
        paragraph.setColor(TextColor.DEFAULT);
        t.setParagraph(paragraph);
    }
}
