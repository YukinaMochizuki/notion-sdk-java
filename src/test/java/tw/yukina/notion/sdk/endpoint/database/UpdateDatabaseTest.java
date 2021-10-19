package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.template.project.Project;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UpdateDatabaseTest extends ModelTest {

    @Test
    void callValue() {
        PageParent pageParent = new PageParent();
        pageParent.setPageId("73f76d1a1510408dbca93acd5ff2914f");
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

        Database projectDatabase = CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(projectDatabase);

        Database database = Project.getDatabase(responseJsonNode, projectDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(projectDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);


        RequestUpdateDatabase requestUpdateDatabase = new RequestUpdateDatabase();
        requestUpdateDatabase.setProperties(Thing.getCreateDatabaseProperty(database.getId()));

        Database responseDatabase = UpdateDatabase.callValue(databaseUuid, requestUpdateDatabase,
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabase);

        database = Thing.getDatabase(responseJsonNode, responseDatabase);
        serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}