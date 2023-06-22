package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.endpoint.block.DeleteBlock;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.template.project.Project;
import tw.yukina.notion.sdk.model.template.project.Thing;

import static org.junit.jupiter.api.Assertions.*;

class CreateDatabaseTest extends ModelTest {

//    @Test
    void createProjectDatabase() {

        PageParent pageParent = new PageParent();
        pageParent.setPageId("73f76d1a1510408dbca93acd5ff2914f");
        pageParent.setParentType(ParentType.PAGE);

        RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Project (Test DB)"));
        requestCreateDatabase.setProperties(Project.getCreateDatabaseProperty());

        DatabaseModel responseDatabaseModel = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabaseModel);

        DatabaseModel databaseModel = Project.getDatabase(responseJsonNode, responseDatabaseModel);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(responseDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);
    }

//    @Test
    void createThingDatabase() {

        PageParent pageParent = new PageParent();
        pageParent.setPageId("7b6d965aa7a34305bab6b37ec515dd1f");
        pageParent.setParentType(ParentType.PAGE);

        RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Thing (Test DB)"));
        requestCreateDatabase.setProperties(Thing.getCreateDatabaseProperty("fc0ad05fc61045378ba4c852ec930f82"));

        DatabaseModel responseDatabaseModel = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabaseModel);

        DatabaseModel databaseModel = Thing.getDatabase(responseJsonNode, responseDatabaseModel);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(responseDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);

        DeleteBlock.call(responseDatabaseModel.getId(), getOkHttpClient(), getRequestBuilder());
    }
}