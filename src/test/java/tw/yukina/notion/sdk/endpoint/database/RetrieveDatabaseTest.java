package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RetrieveDatabaseTest extends ModelTest {

    @Test
    void callValue() {

        Database responseDatabase = RetrieveDatabase.callValue("9dd0209dba4048c0872e428c8528565e",
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabase);

        Database database = Thing.getDatabase(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}