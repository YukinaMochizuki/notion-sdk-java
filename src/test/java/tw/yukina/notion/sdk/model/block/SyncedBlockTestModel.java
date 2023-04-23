package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SyncedBlockTestModel extends ModelTest {

    @Test
    void syncedBlockTestSource() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/849d8f51aa2e4999bb629be7b2ff6e92");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        SyncedBlockModel syncedBlock = SyncedBlockModel.of();
        syncedBlock.setId(block.getId());
        syncedBlock.setCreatedTime(block.getCreatedTime());
        syncedBlock.setLastEditedTime(block.getLastEditedTime());
        syncedBlock.setHasChildren(true);
        syncedBlock.setParent(block.getParent());
        syncedBlock.setCreatedBy(block.getCreatedBy());
        syncedBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(syncedBlock);

        assertEquals(block, syncedBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void syncedBlockTestReference() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/16561c55621f436cae34175f5cb82389");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        SyncedBlockModel syncedBlock = SyncedBlockModel.of("849d8f51-aa2e-4999-bb62-9be7b2ff6e92");
        syncedBlock.setId(block.getId());
        syncedBlock.setCreatedTime(block.getCreatedTime());
        syncedBlock.setLastEditedTime(block.getLastEditedTime());
        syncedBlock.setHasChildren(true);
        syncedBlock.setParent(block.getParent());
        syncedBlock.setCreatedBy(block.getCreatedBy());
        syncedBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(syncedBlock);

        assertEquals(block, syncedBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}