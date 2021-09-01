package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListBlockTest extends ModelTest {

    @Test
    void bulletedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/21c4ce4177714878bddb3bea7b51c8f9");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block bulletedListBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("12345"));
        bulletedListBlock.setId(block.getId());
        bulletedListBlock.setCreatedTime(block.getCreatedTime());
        bulletedListBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(bulletedListBlock);

        assertEquals(block, bulletedListBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void numberedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/6e9f306c7cfe4dff9347bd2f93f358e8");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block numberedListBlock = ListBlockHelper
                .createDefaultNumberedList(RichTextHelper.createDefaultArrayText("The one"));
        numberedListBlock.setId(block.getId());
        numberedListBlock.setCreatedTime(block.getCreatedTime());
        numberedListBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(numberedListBlock);

        assertEquals(block, numberedListBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void toggleListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/353fe62adf4542349c3d95ee6a37ac6e");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block toggleBlock = ListBlockHelper
                .createDefaultToggle(RichTextHelper.createDefaultArrayText("Toggle"));
        toggleBlock.setId(block.getId());
        toggleBlock.setCreatedTime(block.getCreatedTime());
        toggleBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(toggleBlock);

        assertEquals(block, toggleBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
