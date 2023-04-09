package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.template.BlockTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AppendBlockChildrenTest extends ModelTest {

    @Test
    void callValue() throws Exception {
        RequestAppendChildrenBlockList requestAppendChildrenBlockList = new RequestAppendChildrenBlockList();
        requestAppendChildrenBlockList.setBlocks(BlockTemplate.getPageBlock());

        ResponseBlockList responseBlockList = AppendBlockChildren.callValue("944dba913ddc4153bf592ed5d78c8bac",
                requestAppendChildrenBlockList, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        ResponseBlockList testResponseBlockList = new ResponseBlockList();
        testResponseBlockList.setBlocks(BlockTemplate.getPageBlock());
        testResponseBlockList.setType(ObjectType.BLOCK);

        for(int i = 0; i < responseBlockList.getBlocks().size(); i++){
            Block testBlock = testResponseBlockList.getBlocks().get(i);
            Block responseBlock = responseBlockList.getBlocks().get(i);
            testBlock.setId(responseBlock.getId());
            testBlock.setCreatedTime(responseBlock.getCreatedTime());
            testBlock.setCreatedBy(responseBlock.getCreatedBy());
            testBlock.setLastEditedTime(responseBlock.getLastEditedTime());
            testBlock.setLastEditedBy(responseBlock.getLastEditedBy());
            testBlock.setParent(responseBlock.getParent());
        }

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(testResponseBlockList);
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseBlockList);

        assertEquals(responseJsonNode, serializedJsonNode);
        assertEquals(responseBlockList, testResponseBlockList);

        for(int i = 0; i < responseBlockList.getBlocks().size(); i++)
            DeleteBlock.callValue(testResponseBlockList.getBlocks().get(i).getId(),
                    getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        responseBlockList = RetrieveBlockChildren.callValue("944dba913ddc4153bf592ed5d78c8bac",
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        assertTrue(responseBlockList.getBlocks().isEmpty());
    }
}
