package tw.yukina.notion.sdk.model.block.list;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.Paragraph;
import tw.yukina.notion.sdk.model.block.TextBlock;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

public class ListBlockHelper {

    @NotNull
    public static BulletedListBlock createDefaultBulletedList(List<RichText> texts){
        BulletedListBlock bulletedListBlock = new BulletedListBlock();
        initDefaultListBlock(texts, bulletedListBlock);
        bulletedListBlock.setType(BlockType.BULLETED_LIST_ITEM);

        return bulletedListBlock;
    }

    @NotNull
    public static NumberedListBlock createDefaultNumberedList(List<RichText> texts){
        NumberedListBlock numberedListBlock = new NumberedListBlock();
        initDefaultListBlock(texts, numberedListBlock);
        numberedListBlock.setType(BlockType.NUMBERED_LIST_ITEM);

        return numberedListBlock;
    }

    @NotNull
    public static ToggleBlock createDefaultToggle(List<RichText> texts){
        ToggleBlock toggleBlock = new ToggleBlock();
        initDefaultListBlock(texts, toggleBlock);
        toggleBlock.setType(BlockType.TOGGLE);

        return toggleBlock;
    }

    public static <T extends TextBlock> void initDefaultListBlock(List<RichText> texts, T t){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(texts);
        t.setParagraph(paragraph);
    }
}
