package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TableOfContentsBlockTest extends ModelTest {

    @Test
    void tableOfContentsBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/2f030b90108b48b7b67ef1aa1a96030c");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        TableOfContentsBlock tableOfContentsBlock = TableOfContentsBlock.of();
        tableOfContentsBlock.setId(block.getId());
        tableOfContentsBlock.setCreatedTime(block.getCreatedTime());
        tableOfContentsBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(tableOfContentsBlock);

        assertEquals(block, tableOfContentsBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}