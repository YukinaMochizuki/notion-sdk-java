package tw.yukina.notion.sdk.endpoint.block;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.endpoint.block.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.template.BlockTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AppendBlockChildrenTest extends ModelTest {

    @Test
    void callValue() throws Exception {
        RequestChildrenBlockList requestChildrenBlockList = new RequestChildrenBlockList();
        requestChildrenBlockList.setBlocks(BlockTemplate.getPageBlock());

        ResponseBlockList responseBlockList = AppendBlockChildren.callValue("49a92380b1634d28bb580816bb9126b5",
                requestChildrenBlockList, getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());

        ResponseBlockList testResponseBlockList = new ResponseBlockList();
        testResponseBlockList.setBlocks(BlockTemplate.getPageBlock());

        for(int i = 0; i < responseBlockList.getBlocks().size(); i++){
            Block testBlock = testResponseBlockList.getBlocks().get(i);
            Block responseBlock = responseBlockList.getBlocks().get(i);
            testBlock.setId(responseBlock.getId());
            testBlock.setCreatedTime(responseBlock.getCreatedTime());
            testBlock.setLastEditedTime(responseBlock.getLastEditedTime());
        }

        assertEquals(responseBlockList, testResponseBlockList);

        for(int i = 0; i < responseBlockList.getBlocks().size(); i++)
            DeleteBlock.callValue(testResponseBlockList.getBlocks().get(i).getId(),
                    getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());


        responseBlockList = RetrieveBlockChildren.callValue("49a92380b1634d28bb580816bb9126b5",
                getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());

        assertTrue(responseBlockList.getBlocks().isEmpty());
    }
}
