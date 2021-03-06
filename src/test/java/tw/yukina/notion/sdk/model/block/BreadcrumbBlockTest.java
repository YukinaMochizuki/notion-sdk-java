package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class BreadcrumbBlockTest extends ModelTest {

    @Test
    void breadcrumbBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/9218172239894b0dadb40b06ae0cad33");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        BreadcrumbBlock breadcrumbBlock = BreadcrumbBlock.of();
        breadcrumbBlock.setId(block.getId());
        breadcrumbBlock.setCreatedTime(block.getCreatedTime());
        breadcrumbBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(breadcrumbBlock);

        assertEquals(block, breadcrumbBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}