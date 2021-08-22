package tw.yukina.notion.sdk.model.block.heading;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import java.util.List;

public class HeadingBlockHelper {
    @NotNull
    public static HeadingOneBlock createDefaultHeadingOne(List<RichText> texts){
        HeadingOneBlock headingOneBlock = new HeadingOneBlock();
        initDefaultHeadingBlock(texts, headingOneBlock);
        headingOneBlock.setType(BlockType.HEADING_1);

        return headingOneBlock;
    }

    @NotNull
    public static HeadingTwoBlock createDefaultHeadingTwo(List<RichText> texts){
        HeadingTwoBlock headingTwoBlock = new HeadingTwoBlock();
        initDefaultHeadingBlock(texts, headingTwoBlock);
        headingTwoBlock.setType(BlockType.HEADING_2);

        return headingTwoBlock;
    }

    @NotNull
    public static HeadingThreeBlock createDefaultHeadingThree(List<RichText> texts){
        HeadingThreeBlock headingThreeBlock = new HeadingThreeBlock();
        initDefaultHeadingBlock(texts, headingThreeBlock);
        headingThreeBlock.setType(BlockType.HEADING_3);

        return headingThreeBlock;
    }

    public static <T extends HeadingBlock> void initDefaultHeadingBlock(List<RichText> texts, T t){
        Heading heading = new Heading();
        heading.setTexts(texts);
        t.setHeading(heading);
    }
}
