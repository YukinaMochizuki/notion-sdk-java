package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.template.project.Thing;

import static org.junit.jupiter.api.Assertions.*;

class RetrieveDatabaseTestModel extends ModelTest {

    @Test
    void callValue() {

        DatabaseModel responseDatabaseModel = RetrieveDatabase.callValue("9dd0209dba4048c0872e428c8528565e",
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabaseModel);

        DatabaseModel databaseModel = Thing.getDatabase(responseJsonNode, responseDatabaseModel);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(responseDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}