package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.io.IOException;

class BlockTest extends ModelTest {

    @Test
    void getParagraphBlock() {
        try {
            Response response = getResponse( BASE_URL + "/blocks/65d859d0c84d406398b902060a5b68d5");
            Block block = readValueUseCommonObjectMapper(response, Block.class);
            System.out.println(block.toString());
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}