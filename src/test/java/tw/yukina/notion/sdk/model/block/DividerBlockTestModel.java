package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DividerBlockTestModel extends ModelTest {

    @Test
    void dividerBlockTest() throws Exception {
        Response response = getResponse(BASE_URL + "/blocks/99edf98ff9544ad48df27cd8cb480958");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        DividerBlockModel dividerBlock = DividerBlockModel.of();
        dividerBlock.setId(block.getId());
        dividerBlock.setCreatedTime(block.getCreatedTime());
        dividerBlock.setLastEditedTime(block.getLastEditedTime());
        dividerBlock.setParent(block.getParent());
        dividerBlock.setCreatedBy(block.getCreatedBy());
        dividerBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(dividerBlock);

        assertEquals(block, dividerBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}