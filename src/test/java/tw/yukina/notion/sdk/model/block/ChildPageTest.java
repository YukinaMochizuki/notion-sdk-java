package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.BlockHelper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildPageTest extends ModelTest {
    @Test
    void childPageTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/3ddec0b97fcb46fcae784afb2406ce90");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block childPageBlock = BlockHelper.createDefaultChildPageBlock("Readonly Page");
        childPageBlock.setHasChildren(true);
        childPageBlock.setId(block.getId());
        childPageBlock.setCreatedTime(block.getCreatedTime());
        childPageBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(childPageBlock);

        assertEquals(block, childPageBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void childDatabaseTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/287e5fe937aa4fbd91073e9ebe6295a1");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block childDatabaseBlock = BlockHelper.createDefaultDatabasePageBlock("Readonly Database");
        childDatabaseBlock.setHasChildren(false);
        childDatabaseBlock.setId(block.getId());
        childDatabaseBlock.setCreatedTime(block.getCreatedTime());
        childDatabaseBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(childDatabaseBlock);

        assertEquals(block, childDatabaseBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
