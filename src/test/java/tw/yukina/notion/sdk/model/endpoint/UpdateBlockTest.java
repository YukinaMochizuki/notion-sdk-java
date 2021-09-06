package tw.yukina.notion.sdk.model.endpoint;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.block.RetrieveBlock;
import tw.yukina.notion.sdk.endpoint.block.UpdateBlock;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockHelper;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateBlockTest extends ModelTest {

    @Test
    void test() throws IOException, NotionAPIException {
        Block block = RetrieveBlock.callValue("65d859d0c84d406398b902060a5b68d5", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(block.getId());
        paragraphBlock.setCreatedTime(block.getCreatedTime());
        paragraphBlock.setLastEditedTime(block.getLastEditedTime());

        assertEquals(block, paragraphBlock);
    }

    @Test
    void callValueTest() throws IOException, NotionAPIException {
        Block editBlock = BlockHelper.createDefaultParagraph("Edited");

        Block responseBlock = UpdateBlock.callValue("842ab92d84484f2f96b522fb0563794e", editBlock,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Edited");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());

        assertEquals(responseBlock, paragraphBlock);


        Block restoreBlock = BlockHelper.createDefaultParagraph("Paragraph Block");

        Block responseRestoreBlock = UpdateBlock.callValue("842ab92d84484f2f96b522fb0563794e", restoreBlock,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());

        assertEquals(responseRestoreBlock, paragraphBlock);
    }
}
