package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildPageTest extends ModelTest {
    @Test
    void childPageTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/64a90623-37a7-4e0e-85b1-e76de5485740");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block childPageBlock = BlockHelper.createDefaultChildPageBlock("Writable");
        childPageBlock.setId("64a90623-37a7-4e0e-85b1-e76de5485740");
        childPageBlock.setHasChildren(true);

        assertEquals(block, childPageBlock);
        response.close();
    }
}
