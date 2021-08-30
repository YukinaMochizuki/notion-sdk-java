package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBlockTest extends ModelTest {

    @Test
    void todoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/b350bc7a1cee493bba9d84c582c471e7");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Todo list"), false);
        todoBlock.setId("b350bc7a-1cee-493b-ba9d-84c582c471e7");

        assertEquals(block, todoBlock);
        response.close();
    }

    @Test
    void todoCheckedTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/fee4369dd54d42fa862db2a8ecae6859");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Checked"), true);
        todoBlock.setId("fee4369d-d54d-42fa-862d-b2a8ecae6859");

        assertEquals(block, todoBlock);
        response.close();
    }
}
