package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.AbstractEndpoint;
import tw.yukina.notion.sdk.model.database.Database;

public class AbstractDatabaseEndpoint extends AbstractEndpoint {

    public static final String PATH = "/databases/";

    public static Database toDatabase(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(objectNode.toString(), Database.class);
    }

}
