package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListBlockTest extends ModelTest {

    @Test
    void bulletedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/21c4ce4177714878bddb3bea7b51c8f9");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block bulletedListBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("12345"));
        bulletedListBlock.setId("21c4ce41-7771-4878-bddb-3bea7b51c8f9");

        assertEquals(block, bulletedListBlock);
        response.close();
    }
}
