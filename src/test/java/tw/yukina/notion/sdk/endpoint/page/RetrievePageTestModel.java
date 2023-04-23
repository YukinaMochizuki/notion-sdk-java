package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.page.PageModel;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RetrievePageTestModel extends ModelTest {

    @Test
    void callValue() throws IOException, NotionAPIException {
        PageModel responsePageModel = RetrievePage.callValue("e8df4eb760e047b88808974fb12b0ef3", getOkHttpClient(),
                getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responsePageModel);

        PageModel pageModel = new PageModel();
        pageModel.setId(responsePageModel.getId());
        pageModel.setArchived(false);
        pageModel.setCreatedTime(responsePageModel.getCreatedTime());
        pageModel.setCreatedBy(responsePageModel.getCreatedBy());
        pageModel.setLastEditedTime(responsePageModel.getLastEditedTime());
        pageModel.setLastEditedBy(responsePageModel.getLastEditedBy());
        pageModel.setUrl(responsePageModel.getUrl());

        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setDatabaseId("9dd0209d-ba40-48c0-872e-428c8528565e");
        databaseParent.setParentType(ParentType.DATABASE);
        pageModel.setParent(databaseParent);
        pageModel.setPropertyMap(Thing.getPageProperty(responseJsonNode));

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(pageModel);

        assertEquals(responsePageModel, pageModel);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}