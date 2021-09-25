package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest extends ModelTest {

    @Test
    void databaseTest() throws IOException {
        Response response = getResponse( BASE_URL + "/databases/1e6ee2b45bfd41ad9ff35f397e580afd", getRequestBuilder());
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Database responseDatabase = readValueUseCommonObjectMapper(tree, Database.class);

        Database database = Thing.getDatabase(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

//        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}
