package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class EquationBlockTest extends ModelTest {

    @Test
    void equationBlockTest() throws Exception{
        Response response = getResponse( BASE_URL + "/blocks/87f122f0b27045f1bf4105726c76f6fa");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        EquationBlock equationBlock = EquationBlock.of("E=MC^2");
        equationBlock.setId(block.getId());
        equationBlock.setCreatedTime(block.getCreatedTime());
        equationBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(equationBlock);

        assertEquals(block, equationBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}