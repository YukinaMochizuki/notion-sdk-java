package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteBlockTest extends ModelTest {

    @Test
    void quoteTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/b1498ae80fe04ac4b03915c938c4c900");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Quote String\nString new line");
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(richTexts);

        QuoteBlock quoteBlock = new QuoteBlock();
        quoteBlock.setId(block.getId());
        quoteBlock.setCreatedTime(block.getCreatedTime());
        quoteBlock.setLastEditedTime(block.getLastEditedTime());
        quoteBlock.setType(BlockType.QUOTE);
        quoteBlock.setParagraph(paragraph);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(quoteBlock);

        assertEquals(block, quoteBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

}
