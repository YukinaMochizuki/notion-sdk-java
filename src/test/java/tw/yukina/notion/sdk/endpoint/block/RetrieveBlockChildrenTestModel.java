package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.helper.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetrieveBlockChildrenTestModel extends ModelTest {

    @Test
    void callValue() {
        ResponseBlockList responseBlockList = RetrieveBlockChildren.callValue("53b516396e8148d7be0a30f43ccf5166", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());

        JsonNode responseJsonNode = RetrieveBlockChildren.callTree("53b516396e8148d7be0a30f43ccf5166", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());

        ResponseBlockList blockList = new ResponseBlockList();
        List<BlockModel> blocks = blockList.getBlocks();

        BlockModel addBlock = BlockHelper.createDefaultParagraph("Text");
        addBlock.setId(responseBlockList.getBlocks().get(0).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(0).getCreatedTime());
        addBlock.setCreatedBy(responseBlockList.getBlocks().get(0).getCreatedBy());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(0).getLastEditedTime());
        addBlock.setLastEditedBy(responseBlockList.getBlocks().get(0).getLastEditedBy());
        addBlock.setParent(responseBlockList.getBlocks().get(0).getParent());
        blocks.add(addBlock);

        addBlock = BlockHelper.createDefaultParagraph("Text2");
        addBlock.setId(responseBlockList.getBlocks().get(1).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(1).getCreatedTime());
        addBlock.setCreatedBy(responseBlockList.getBlocks().get(1).getCreatedBy());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(1).getLastEditedTime());
        addBlock.setLastEditedBy(responseBlockList.getBlocks().get(1).getLastEditedBy());
        addBlock.setParent(responseBlockList.getBlocks().get(1).getParent());
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 1"));
        addBlock.setId(responseBlockList.getBlocks().get(2).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(2).getCreatedTime());
        addBlock.setCreatedBy(responseBlockList.getBlocks().get(2).getCreatedBy());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(2).getLastEditedTime());
        addBlock.setLastEditedBy(responseBlockList.getBlocks().get(2).getLastEditedBy());
        addBlock.setParent(responseBlockList.getBlocks().get(2).getParent());
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 2"));
        addBlock.setId(responseBlockList.getBlocks().get(3).getId());
        addBlock.setCreatedTime(responseBlockList.getBlocks().get(3).getCreatedTime());
        addBlock.setCreatedBy(responseBlockList.getBlocks().get(3).getCreatedBy());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(3).getLastEditedTime());
        addBlock.setLastEditedBy(responseBlockList.getBlocks().get(3).getLastEditedBy());
        addBlock.setParent(responseBlockList.getBlocks().get(3).getParent());
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
        addBlock.setCreatedBy(responseBlockList.getBlocks().get(4).getCreatedBy());
        addBlock.setLastEditedTime(responseBlockList.getBlocks().get(4).getLastEditedTime());
        addBlock.setLastEditedBy(responseBlockList.getBlocks().get(4).getLastEditedBy());
        addBlock.setParent(responseBlockList.getBlocks().get(4).getParent());
        blocks.add(addBlock);

        assertEquals(responseBlockList, blockList);
    }
}
