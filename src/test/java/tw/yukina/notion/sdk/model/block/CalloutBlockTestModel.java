package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.common.IconEmoji;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalloutBlockTestModel extends ModelTest {

    @Test
    void calloutTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/7f1650a2ee8747f4aedb15b876be7eb1");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        IconEmoji iconEmoji = new IconEmoji();
        iconEmoji.setType("emoji");
        iconEmoji.setEmoji("\uD83D\uDCA1");

        Callout callout = new Callout();
        callout.setRichTexts(RichTextHelper.createDefaultArrayText("Callout\nTest"));
        callout.setIcon(iconEmoji);
        callout.setColor(TextColor.GRAY_BACKGROUND);

        CalloutBlockModel calloutBlock = new CalloutBlockModel();
        calloutBlock.setId(block.getId());
        calloutBlock.setType(BlockType.CALLOUT);
        calloutBlock.setCreatedTime(block.getCreatedTime());
        calloutBlock.setLastEditedTime(block.getLastEditedTime());
        calloutBlock.setCallout(callout);
        calloutBlock.setHasChildren(true);
        calloutBlock.setParent(block.getParent());
        calloutBlock.setCreatedBy(block.getCreatedBy());
        calloutBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(calloutBlock);

        assertEquals(block, calloutBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
