package tw.yukina.notion.sdk.model.endpoint;

import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.block.AppendBlockChildren;
import tw.yukina.notion.sdk.endpoint.block.DeleteBlock;
import tw.yukina.notion.sdk.endpoint.block.RetrieveBlockChildren;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.Template;
import tw.yukina.notion.sdk.model.block.Block;

import static org.junit.jupiter.api.Assertions.*;

public class AppendBlockChildrenTest extends ModelTest {

    @Test
    void callValue() throws Exception {
        RequestChildrenBlockList requestChildrenBlockList = new RequestChildrenBlockList();
        requestChildrenBlockList.setBlocks(Template.getPageBlock());

        ResponseBlockList responseBlockList = AppendBlockChildren.callValue("49a92380b1634d28bb580816bb9126b5",
                requestChildrenBlockList, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        ResponseBlockList testResponseBlockList = new ResponseBlockList();
        testResponseBlockList.setBlocks(Template.getPageBlock());

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
                    getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());


        responseBlockList = RetrieveBlockChildren.callValue("49a92380b1634d28bb580816bb9126b5",
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());

        assertTrue(responseBlockList.getBlocks().isEmpty());
    }
}
