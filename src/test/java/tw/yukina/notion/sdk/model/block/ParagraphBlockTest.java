package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

class ParagraphBlockTest extends ModelTest {

    @Test
    void plainTextTest() {
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

    @Test
    void personMentionTest(){
        try {
            Response response = getResponse( BASE_URL + "/blocks/302cd1a8-c9d3-413c-875a-167fed0cdbb0");
            Block block = readValueUseCommonObjectMapper(response, Block.class);

            System.out.println(block);

//            assertEquals(block, paragraphBlock);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    }
}