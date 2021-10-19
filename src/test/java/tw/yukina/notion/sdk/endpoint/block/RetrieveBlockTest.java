package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetrieveBlockTest extends ModelTest {

    @Test
    void callValue() {
        Block block = RetrieveBlock.callValue("65d859d0c84d406398b902060a5b68d5", getOkHttpClient(),
                getAnotherRequestBuilder(), getCommonObjectMapper());

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(block.getId());
        paragraphBlock.setCreatedTime(block.getCreatedTime());
        paragraphBlock.setLastEditedTime(block.getLastEditedTime());

        assertEquals(block, paragraphBlock);
    }
}
