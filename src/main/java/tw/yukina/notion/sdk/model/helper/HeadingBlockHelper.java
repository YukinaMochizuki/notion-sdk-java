package tw.yukina.notion.sdk.model.helper;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.heading.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Deprecated
public class HeadingBlockHelper {
    @NotNull
    public static HeadingOneBlockModel createDefaultHeadingOne(List<RichText> texts) {
        HeadingOneBlockModel headingOneBlock = new HeadingOneBlockModel();
        initDefaultHeadingBlock(texts, headingOneBlock);
        headingOneBlock.setType(BlockType.HEADING_1);

        return headingOneBlock;
    }

    @NotNull
    public static HeadingTwoBlockModel createDefaultHeadingTwo(List<RichText> texts) {
        HeadingTwoBlockModel headingTwoBlock = new HeadingTwoBlockModel();
        initDefaultHeadingBlock(texts, headingTwoBlock);
        headingTwoBlock.setType(BlockType.HEADING_2);

        return headingTwoBlock;
    }

    @NotNull
    public static HeadingThreeBlockModel createDefaultHeadingThree(List<RichText> texts) {
        HeadingThreeBlockModel headingThreeBlock = new HeadingThreeBlockModel();
        initDefaultHeadingBlock(texts, headingThreeBlock);
        headingThreeBlock.setType(BlockType.HEADING_3);

        return headingThreeBlock;
    }

    public static <T extends HeadingBlock> void initDefaultHeadingBlock(List<RichText> texts, @NotNull T t) {
        NotToggleHeading heading = new NotToggleHeading();
        heading.setRichTexts(texts);
        heading.setColor(TextColor.DEFAULT);
        t.setHeading(heading);
    }
}
