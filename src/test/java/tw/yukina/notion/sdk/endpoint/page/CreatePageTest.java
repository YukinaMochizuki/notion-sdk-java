package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.node.ObjectNode;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;

class CreatePageTest extends ModelTest {

//    @Test
    void callValue() throws IOException, NotionAPIException {

        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setId("31d2e694-db36-4822-98a4-7dfa29cde551");
        databaseParent.setParentType(ParentType.DATABASE);

        RequestCreatePage requestCreatePage = new RequestCreatePage();
        requestCreatePage.setParent(databaseParent);
        requestCreatePage.setProperties(Thing.getCreatePageProperty());

        ObjectNode objectNode = getCommonObjectMapper().valueToTree(requestCreatePage);
        ObjectNode parent = (ObjectNode) objectNode.get("parent");
        parent.remove("type");
        System.out.println(objectNode.toPrettyString());

        CreatePage.callValue(objectNode, getOkHttpClient(), getAnotherRequestBuilder(), getCommonObjectMapper());
    }
}