package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.template.project.Project;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateDatabaseTest extends ModelTest {

//    @Test
    void createProjectDatabase() throws IOException, NotionAPIException {

        PageParent pageParent = new PageParent();
        pageParent.setPageId("73f76d1a1510408dbca93acd5ff2914f");
        pageParent.setParentType(ParentType.PAGE);

        RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Project (Test DB)"));
        requestCreateDatabase.setProperties(Project.getCreateDatabaseProperty());

        Database responseDatabase = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabase);

        Database database = Project.getDatabase(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }

//    @Test
    void createThingDatabase() throws IOException, NotionAPIException {

        PageParent pageParent = new PageParent();
        pageParent.setPageId("73f76d1a1510408dbca93acd5ff2914f");
        pageParent.setParentType(ParentType.PAGE);

        RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Thing (Test DB)"));
        requestCreateDatabase.setProperties(Thing.getCreateDatabaseProperty("b104b583b1c543c884866a24a3d812c3"));

        Database responseDatabase = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabase);

        Database database = Project.getDatabase(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}