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

public class TodoBlockTestModel extends ModelTest {

    @Test
    void todoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/a643395e3b694f4b935929837299ba8e");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BlockModel todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Todo list"), false);
        todoBlock.setId(block.getId());
        todoBlock.setCreatedTime(block.getCreatedTime());
        todoBlock.setLastEditedTime(block.getLastEditedTime());
        todoBlock.setParent(block.getParent());
        todoBlock.setCreatedBy(block.getCreatedBy());
        todoBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(todoBlock);

        assertEquals(block, todoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void todoCheckedTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/526a61e5cfdb4ddc906fe842a6760fd4");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BlockModel todoBlock = BlockHelper
                .createDefaultTodoBlock(RichTextHelper.createDefaultArrayText("Checked"), true);
        todoBlock.setId(block.getId());
        todoBlock.setCreatedTime(block.getCreatedTime());
        todoBlock.setLastEditedTime(block.getLastEditedTime());
        todoBlock.setParent(block.getParent());
        todoBlock.setCreatedBy(block.getCreatedBy());
        todoBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(todoBlock);

        assertEquals(block, todoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
