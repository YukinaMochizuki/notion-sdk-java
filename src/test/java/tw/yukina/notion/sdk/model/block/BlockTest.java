package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class BlockTest extends ModelTest {

    @Test
    void getParagraphBlock() {
        try {
            Response response = getResponse( BASE_URL + "/blocks/65d859d0c84d406398b902060a5b68d5");
            Block block = readValueUseCommonObjectMapper(response, Block.class);

            ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
            paragraphBlock.setId("65d859d0-c84d-4063-98b9-02060a5b68d5");

            assertEquals(block, paragraphBlock);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}