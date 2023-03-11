package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TemplateBlockTest extends ModelTest {

    @Test
    void templateBlock() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/f43a5316ff03445cb8de1bb24d6fe80b");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        TemplateBlock templateBlock = TemplateBlock.of(RichTextHelper.createDefaultArrayText("Add a new to-do"));
        templateBlock.setId(block.getId());
        templateBlock.setCreatedTime(block.getCreatedTime());
        templateBlock.setLastEditedTime(block.getLastEditedTime());
        templateBlock.setHasChildren(true);
        templateBlock.setParent(block.getParent());
        templateBlock.setCreatedBy(block.getCreatedBy());
        templateBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(templateBlock);

        assertEquals(block, templateBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}