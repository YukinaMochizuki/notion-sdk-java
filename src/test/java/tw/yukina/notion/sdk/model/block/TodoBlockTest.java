package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBlockTest extends ModelTest {

    @Test
    void todoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/b350bc7a1cee493bba9d84c582c471e7");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Todo list"), false);
        todoBlock.setId(block.getId());
        todoBlock.setCreatedTime(block.getCreatedTime());
        todoBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(todoBlock);

        assertEquals(block, todoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void todoCheckedTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/fee4369dd54d42fa862db2a8ecae6859");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Checked"), true);
        todoBlock.setId(block.getId());
        todoBlock.setCreatedTime(block.getCreatedTime());
        todoBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(todoBlock);

        assertEquals(block, todoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
