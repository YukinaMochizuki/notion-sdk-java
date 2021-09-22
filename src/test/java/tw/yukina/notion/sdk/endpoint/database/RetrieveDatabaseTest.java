package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.Template;
import tw.yukina.notion.sdk.model.database.Database;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RetrieveDatabaseTest extends ModelTest {

    @Test
    void callValue() throws IOException, NotionAPIException {

        Database responseDatabase = RetrieveDatabase.callValue("31d2e694db36482298a47dfa29cde551",
                getOkHttpClient(), getRequestBuilder(), getCommonObjectMapper());
        JsonNode responseJsonNode = getCommonObjectMapper().valueToTree(responseDatabase);

        Database database = Template.getDatabaseTemplate(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}