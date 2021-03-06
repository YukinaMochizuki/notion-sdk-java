package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkBlockTest extends ModelTest {

    @Test
    void bookmarkBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/2a9f78da32014634a8368e0430bf4f1f");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        BookmarkBlock bookmarkBlock = BookmarkBlock.of("http://www.google.com", RichTextHelper.createDefaultArrayText("This is caption"));
        bookmarkBlock.setId(block.getId());
        bookmarkBlock.setCreatedTime(block.getCreatedTime());
        bookmarkBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(bookmarkBlock);

        assertEquals(block, bookmarkBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}