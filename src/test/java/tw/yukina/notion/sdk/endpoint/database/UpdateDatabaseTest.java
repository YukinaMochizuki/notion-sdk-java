package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.template.project.Project;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDatabaseTest extends ModelTest {

//    @Test
    void callValue() {
        PageParent pageParent = new PageParent();
        pageParent.setPageId("7b6d965aa7a34305bab6b37ec515dd1f");
        pageParent.setParentType(ParentType.PAGE);

        RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Thing (Test DB)"));
        requestCreateDatabase.setProperties(new HashMap<>());

        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setId("title");
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        requestCreateDatabase.getProperties().put("Name", databaseProperty);

        String databaseUuid = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper()).getId();

        //Create Thing Parent Database
        requestCreateDatabase = new RequestCreateDatabase();
        requestCreateDatabase.setParent(pageParent);
        requestCreateDatabase.setTitle(RichTextHelper.createDefaultArrayText("Project (Test DB)"));
        requestCreateDatabase.setProperties(Project.getCreateDatabaseProperty());

        DatabaseModel projectDatabaseModel = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(projectDatabaseModel);

        DatabaseModel databaseModel = Project.getDatabase(responseJsonNode, projectDatabaseModel);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(projectDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);


        RequestUpdateDatabase requestUpdateDatabase = new RequestUpdateDatabase();
        requestUpdateDatabase.setProperties(Thing.getCreateDatabaseProperty(databaseModel.getId()));

        DatabaseModel responseDatabaseModel = UpdateDatabase.callValue(databaseUuid, requestUpdateDatabase,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabaseModel);

        databaseModel = Thing.getDatabase(responseJsonNode, responseDatabaseModel);
        serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(responseDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}