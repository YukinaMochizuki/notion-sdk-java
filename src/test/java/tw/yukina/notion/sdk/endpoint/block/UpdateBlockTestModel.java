package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.ParagraphBlockModel;
import tw.yukina.notion.sdk.model.helper.BlockHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateBlockTestModel extends ModelTest {

    @Test
    void callValue() {
        BlockModel editBlock = BlockHelper.createDefaultParagraph("After");

        BlockModel responseBlock = UpdateBlock.callValue("5eef55515c284f78a43bbcc6e3a43906", editBlock,
                getOkHttpClient(), getRequestBuilder(), getIncludeNullObjectMapper());

        ParagraphBlockModel paragraphBlock = BlockHelper.createDefaultParagraph("After");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setCreatedBy(responseBlock.getCreatedBy());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());
        paragraphBlock.setLastEditedBy(responseBlock.getLastEditedBy());
        paragraphBlock.setParent(responseBlock.getParent());

        assertEquals(responseBlock, paragraphBlock);


        BlockModel restoreBlock = BlockHelper.createDefaultParagraph("Before");

        BlockModel responseRestoreBlock = UpdateBlock.callValue("5eef55515c284f78a43bbcc6e3a43906", restoreBlock,
                getOkHttpClient(), getRequestBuilder(), getIncludeNullObjectMapper());

        paragraphBlock = BlockHelper.createDefaultParagraph("Before");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setCreatedBy(responseBlock.getCreatedBy());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());
        paragraphBlock.setLastEditedBy(responseBlock.getLastEditedBy());
        paragraphBlock.setParent(responseBlock.getParent());

        assertEquals(responseRestoreBlock, paragraphBlock);
    }
}
