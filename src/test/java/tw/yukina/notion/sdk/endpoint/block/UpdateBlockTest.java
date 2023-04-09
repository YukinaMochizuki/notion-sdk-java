package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateBlockTest extends ModelTest {

    @Test
    void callValue() {
        Block editBlock = BlockHelper.createDefaultParagraph("After");

        Block responseBlock = UpdateBlock.callValue("5eef55515c284f78a43bbcc6e3a43906", editBlock,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("After");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setCreatedBy(responseBlock.getCreatedBy());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());
        paragraphBlock.setLastEditedBy(responseBlock.getLastEditedBy());
        paragraphBlock.setParent(responseBlock.getParent());

        assertEquals(responseBlock, paragraphBlock);


        Block restoreBlock = BlockHelper.createDefaultParagraph("Before");

        Block responseRestoreBlock = UpdateBlock.callValue("5eef55515c284f78a43bbcc6e3a43906", restoreBlock,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

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
