package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListBlockTestModel extends ModelTest {

    @Test
    void bulletedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/c8d94808dba84ffebc4841feaf3f5880");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BlockModel bulletedListBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("12345"));
        bulletedListBlock.setId(block.getId());
        bulletedListBlock.setCreatedTime(block.getCreatedTime());
        bulletedListBlock.setLastEditedTime(block.getLastEditedTime());
        bulletedListBlock.setParent(block.getParent());
        bulletedListBlock.setCreatedBy(block.getCreatedBy());
        bulletedListBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(bulletedListBlock);

        assertEquals(block, bulletedListBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void numberedListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/b9c51fd99154434ea677c62bb97482ad");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BlockModel numberedListBlock = ListBlockHelper
                .createDefaultNumberedList(RichTextHelper.createDefaultArrayText("The one"));
        numberedListBlock.setId(block.getId());
        numberedListBlock.setCreatedTime(block.getCreatedTime());
        numberedListBlock.setLastEditedTime(block.getLastEditedTime());
        numberedListBlock.setParent(block.getParent());
        numberedListBlock.setCreatedBy(block.getCreatedBy());
        numberedListBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(numberedListBlock);

        assertEquals(block, numberedListBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void toggleListTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/bc143264521b4388ad71eeb2de463b57");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        BlockModel toggleBlock = ListBlockHelper
                .createDefaultToggle(RichTextHelper.createDefaultArrayText("Toggle"));
        toggleBlock.setId(block.getId());
        toggleBlock.setCreatedTime(block.getCreatedTime());
        toggleBlock.setLastEditedTime(block.getLastEditedTime());
        toggleBlock.setParent(block.getParent());
        toggleBlock.setCreatedBy(block.getCreatedBy());
        toggleBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(toggleBlock);

        assertEquals(block, toggleBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
