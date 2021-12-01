package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SyncedBlockTest extends ModelTest {

    @Test
    void syncedBlockTestSource() throws Exception {
        Response response = getResponse( BASE_URL + "/blocks/849d8f51aa2e4999bb629be7b2ff6e92");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        SyncedBlock syncedBlock = SyncedBlock.of();
        syncedBlock.setId(block.getId());
        syncedBlock.setCreatedTime(block.getCreatedTime());
        syncedBlock.setLastEditedTime(block.getLastEditedTime());
        syncedBlock.setHasChildren(true);
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
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        SyncedBlock syncedBlock = SyncedBlock.of("849d8f51-aa2e-4999-bb62-9be7b2ff6e92");
        syncedBlock.setId(block.getId());
        syncedBlock.setCreatedTime(block.getCreatedTime());
        syncedBlock.setLastEditedTime(block.getLastEditedTime());
        syncedBlock.setHasChildren(true);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(syncedBlock);

        assertEquals(block, syncedBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}