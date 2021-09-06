package tw.yukina.notion.sdk.model.endpoint;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.block.RetrieveBlock;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockHelper;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetrieveBlockTest extends ModelTest {

    @Test
    void callValueTest() throws IOException, NotionAPIException {
        Block block = RetrieveBlock.callValue("65d859d0c84d406398b902060a5b68d5", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(block.getId());
        paragraphBlock.setCreatedTime(block.getCreatedTime());
        paragraphBlock.setLastEditedTime(block.getLastEditedTime());

        assertEquals(block, paragraphBlock);
    }
}
