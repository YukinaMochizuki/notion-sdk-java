package tw.yukina.notion.sdk.model;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockHelper;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.util.ArrayList;
import java.util.List;

public class Template {

    @NotNull
    public static List<Block> getPageBlock() throws Exception {
        List<Block> blocks = new ArrayList<>();

        Block addBlock = BlockHelper.createDefaultParagraph("Text");
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 1"));
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 2"));
        blocks.add(addBlock);

        RichText text = RichTextHelper.createDateMention("2021-08-21");
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        text = RichTextHelper.createDateMention("2021-08-10T10:00:00.000+08:00", "2021-08-28T14:23:00.000+08:00");
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        text = RichTextHelper.createDefaultText("public static void main(String[] args)");
        text.getAnnotations().setCode(true);
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        return blocks;
    }
}