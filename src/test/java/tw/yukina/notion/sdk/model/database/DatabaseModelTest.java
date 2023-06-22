package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.template.project.Thing;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseModelTest extends ModelTest {

    @Test
    void databaseTest() throws IOException, NotionAPIException {
        Response response = getResponse(BASE_URL + "/databases/9dd0209dba4048c0872e428c8528565e", getRequestBuilder());
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        DatabaseModel responseDatabaseModel = readValueUseCommonObjectMapper(tree, DatabaseModel.class);

        DatabaseModel databaseModel = Thing.getDatabase(responseJsonNode, responseDatabaseModel);
        databaseModel.setParent(responseDatabaseModel.getParent());
        databaseModel.setCreatedBy(responseDatabaseModel.getCreatedBy());
        databaseModel.setLastEditedBy(responseDatabaseModel.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(databaseModel);

        assertEquals(responseDatabaseModel, databaseModel);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}
