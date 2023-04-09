package tw.yukina.notion.sdk.model.page;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageTest extends ModelTest {

    @Test
    void databasePageTest() throws IOException {
        Response response = getResponse( BASE_URL + "/pages/e8df4eb760e047b88808974fb12b0ef3");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Page responsePage = readValueUseCommonObjectMapper(tree, Page.class);

        Page page = new Page();
        page.setId(responsePage.getId());
        page.setArchived(false);
        page.setCreatedTime(responsePage.getCreatedTime());
        page.setCreatedBy(responsePage.getCreatedBy());
        page.setLastEditedTime(responsePage.getLastEditedTime());
        page.setLastEditedBy(responsePage.getLastEditedBy());
        page.setUrl(responsePage.getUrl());

        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setDatabaseId("9dd0209d-ba40-48c0-872e-428c8528565e");
        databaseParent.setParentType(ParentType.DATABASE);
        page.setParent(databaseParent);
        page.setPropertyMap(Thing.getPageProperty(responseJsonNode));

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(page);

        assertEquals(responsePage, page);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}