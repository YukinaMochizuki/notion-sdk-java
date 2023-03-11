package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ColumnListBlockTest extends ModelTest {

    @Test
    void columnListBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/df5ff62a3aec4a999ce9568beb353f15");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        ColumnListBlock columnListBlock = ColumnListBlock.of();
        columnListBlock.setId(block.getId());
        columnListBlock.setCreatedTime(block.getCreatedTime());
        columnListBlock.setLastEditedTime(block.getLastEditedTime());
        columnListBlock.setHasChildren(true);
        columnListBlock.setParent(block.getParent());
        columnListBlock.setCreatedBy(block.getCreatedBy());
        columnListBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(columnListBlock);

        assertEquals(block, columnListBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}