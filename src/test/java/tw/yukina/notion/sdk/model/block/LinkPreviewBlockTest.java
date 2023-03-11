package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LinkPreviewBlockTest extends ModelTest {

    @Test
    void linkPreviewBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/173d0be23237464f88f4568910a66e12");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        LinkPreviewBlock linkPreviewBlock = LinkPreviewBlock.of("https://github.com/example/example-repo/pull/1234");
        linkPreviewBlock.setId(block.getId());
        linkPreviewBlock.setCreatedTime(block.getCreatedTime());
        linkPreviewBlock.setLastEditedTime(block.getLastEditedTime());
        linkPreviewBlock.setParent(block.getParent());
        linkPreviewBlock.setCreatedBy(block.getCreatedBy());
        linkPreviewBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(linkPreviewBlock);

        assertEquals(block, linkPreviewBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}