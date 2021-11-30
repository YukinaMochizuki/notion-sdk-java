package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CodeBlockTest extends ModelTest {

    @Test
    void codeBlockTest() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/985595dbe687410ca51c654688a9eed0");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        CodeBlock codeBlock = CodeBlock.of(RichTextHelper.createDefaultArrayText("let i = 12345;\nconsole.log(i);"),
                CodeLanguageType.JAVASCRIPT);
        codeBlock.setId(block.getId());
        codeBlock.setCreatedTime(block.getCreatedTime());
        codeBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(codeBlock);

        assertEquals(block, codeBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();

    }
}