package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookmarkBlockTestModel extends ModelTest {

    @Test
    void bookmarkBlockTest() throws Exception {
        Response response = getResponse(BASE_URL + "/blocks/2a9f78da32014634a8368e0430bf4f1f");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BookmarkBlockModel bookmarkBlock = BookmarkBlockModel.of("http://www.google.com", RichTextHelper.createDefaultArrayText("This is caption"));
        bookmarkBlock.setId(block.getId());
        bookmarkBlock.setCreatedTime(block.getCreatedTime());
        bookmarkBlock.setLastEditedTime(block.getLastEditedTime());
        bookmarkBlock.setParent(block.getParent());
        bookmarkBlock.setCreatedBy(block.getCreatedBy());
        bookmarkBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(bookmarkBlock);

        assertEquals(block, bookmarkBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}