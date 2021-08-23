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

    @Test
    void numberedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/6e9f306c7cfe4dff9347bd2f93f358e8");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block numberedListBlock = ListBlockHelper
                .createDefaultNumberedList(RichTextHelper.createDefaultArrayText("The one"));
        numberedListBlock.setId("6e9f306c-7cfe-4dff-9347-bd2f93f358e8");

        assertEquals(block, numberedListBlock);
        response.close();
    }

    @Test
    void toggleListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/353fe62adf4542349c3d95ee6a37ac6e");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        Block toggleBlock = ListBlockHelper
                .createDefaultToggle(RichTextHelper.createDefaultArrayText("Toggle"));
        toggleBlock.setId("353fe62a-df45-4234-9c3d-95ee6a37ac6e");

        assertEquals(block, toggleBlock);
        response.close();
    }
}
