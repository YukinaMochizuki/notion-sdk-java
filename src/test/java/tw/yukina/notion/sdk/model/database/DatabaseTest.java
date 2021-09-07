package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;

import java.io.IOException;
import java.util.Objects;

public class DatabaseTest extends ModelTest {

    @Test
    void databaseTest() throws IOException {
        Response response = getResponse( BASE_URL + "/databases/8140870de48d4e3d84961647c7810b11");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Database database = readValueUseCommonObjectMapper(tree, Database.class);

        System.out.println(database);
    }
}
