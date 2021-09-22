package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.Template;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest extends ModelTest {

    @Test
    void databaseTest() throws IOException {
        Response response = getResponse( BASE_URL + "/databases/31d2e694db36482298a47dfa29cde551");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Database responseDatabase = readValueUseCommonObjectMapper(tree, Database.class);

        Database database = Template.getDatabaseTemplate(responseJsonNode, responseDatabase);
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}
