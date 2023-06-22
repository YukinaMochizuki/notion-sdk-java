package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.ParagraphBlockModel;
import tw.yukina.notion.sdk.model.helper.BlockHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetrieveBlockTestModel extends ModelTest {

    @Test
    void callValue() {
        BlockModel block = RetrieveBlock.callValue("2adb3a1acf1f43d4a6b3d95be70e0918", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());

        ParagraphBlockModel paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(block.getId());
        paragraphBlock.setCreatedTime(block.getCreatedTime());
        paragraphBlock.setCreatedBy(block.getCreatedBy());
        paragraphBlock.setLastEditedTime(block.getLastEditedTime());
        paragraphBlock.setLastEditedBy(block.getLastEditedBy());
        paragraphBlock.setParent(block.getParent());

        assertEquals(block, paragraphBlock);
    }
}
