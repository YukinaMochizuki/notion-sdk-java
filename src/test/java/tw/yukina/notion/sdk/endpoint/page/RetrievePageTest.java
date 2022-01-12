package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.page.Page;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RetrievePageTest extends ModelTest {

    @Test
    void callValue() throws IOException, NotionAPIException {
        Page responsePage = RetrievePage.callValue("e7eb4611798b4bcc9d6d3838dc84156b", getOkHttpClient(),
                getAnotherRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responsePage);

        Page page = new Page();
        page.setId(responsePage.getId());
        page.setArchived(false);
        page.setCreatedTime(responsePage.getCreatedTime());
        page.setLastEditedTime(responsePage.getLastEditedTime());
        page.setUrl(responsePage.getUrl());

        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setDatabaseId("31d2e694-db36-4822-98a4-7dfa29cde551");
        databaseParent.setParentType(ParentType.DATABASE);
        page.setParent(databaseParent);
        page.setPropertyMap(Thing.getPageProperty(responseJsonNode));

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(page);

        assertEquals(responsePage, page);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}