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
        Response response = getResponse( BASE_URL + "/blocks/64a9062337a74e0e85b1e76de5485740");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block childPageBlock = BlockHelper.createDefaultChildPageBlock("Writable");
        childPageBlock.setHasChildren(true);
        childPageBlock.setId(block.getId());
        childPageBlock.setCreatedTime(block.getCreatedTime());
        childPageBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(childPageBlock);

        assertEquals(block, childPageBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
