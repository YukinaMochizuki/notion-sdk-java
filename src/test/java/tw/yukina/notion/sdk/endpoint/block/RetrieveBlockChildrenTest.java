package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.block.heading.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetrieveBlockChildrenTest extends ModelTest {

    @Test
    void callValue() throws IOException, NotionAPIException {
        ResponseBlockList responseBlockList = RetrieveBlockChildren.callValue("a5277c2eb62c4d58ac2bb3c1df2e39d5", getOkHttpClient(),
                getAnotherRequestBuilder(), getCommonObjectMapper());

        JsonNode responseJsonNode = RetrieveBlockChildren.callTree("a5277c2eb62c4d58ac2bb3c1df2e39d5", getOkHttpClient(),
                getAnotherRequestBuilder(), getCommonObjectMapper());

        ResponseBlockList blockList = new ResponseBlockList();
        List<Block> blocks = blockList.getBlocks();

        Block addBlock = BlockHelper.createDefaultParagraph("Text");
        addBlock.setId(responseBlockList.getBlocks().get(0).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(0).getCreatedTime());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(0).getLastEditedTime());
        blocks.add(addBlock);

        addBlock = BlockHelper.createDefaultParagraph("Text2");
        addBlock.setId(responseBlockList.getBlocks().get(1).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(1).getCreatedTime());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(1).getLastEditedTime());
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 1"));
        addBlock.setId(responseBlockList.getBlocks().get(2).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(2).getCreatedTime());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(2).getLastEditedTime());
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 2"));
        addBlock.setId(responseBlockList.getBlocks().get(3).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(3).getCreatedTime());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(3).getLastEditedTime());
        blocks.add(addBlock);

        List<RichText> texts = new ArrayList<>();
        texts.add(RichTextHelper.createDefaultText("HI "));

        RichText richText = RichTextHelper.createDefaultText("!!");
        richText.getAnnotations().setBold(true);
        richText.getAnnotations().setColor(TextColor.PINK);
        texts.add(richText);

        addBlock = HeadingBlockHelper.createDefaultHeadingOne(texts);
        addBlock.setId(responseBlockList.getBlocks().get(4).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(4).getCreatedTime());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(4).getLastEditedTime());
        blocks.add(addBlock);

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(blockList);

        assertEquals(responseBlockList, blockList);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}
