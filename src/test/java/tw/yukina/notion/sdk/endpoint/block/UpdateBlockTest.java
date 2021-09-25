package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateBlockTest extends ModelTest {

    @Test
    void callValue() throws IOException, NotionAPIException {
        Block editBlock = BlockHelper.createDefaultParagraph("Edited");

        Block responseBlock = UpdateBlock.callValue("842ab92d84484f2f96b522fb0563794e", editBlock,
                getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Edited");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());

        assertEquals(responseBlock, paragraphBlock);


        Block restoreBlock = BlockHelper.createDefaultParagraph("Paragraph Block");

        Block responseRestoreBlock = UpdateBlock.callValue("842ab92d84484f2f96b522fb0563794e", restoreBlock,
                getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());

        paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(responseBlock.getId());
        paragraphBlock.setCreatedTime(responseBlock.getCreatedTime());
        paragraphBlock.setLastEditedTime(responseBlock.getLastEditedTime());

        assertEquals(responseRestoreBlock, paragraphBlock);
    }
}
