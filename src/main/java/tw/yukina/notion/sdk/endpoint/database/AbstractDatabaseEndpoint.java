package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.AbstractEndpoint;
import tw.yukina.notion.sdk.model.database.DatabaseModel;

public abstract class AbstractDatabaseEndpoint extends AbstractEndpoint {

    public static final String PATH = "/databases/";

    public static DatabaseModel toDatabase(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), DatabaseModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void prepareRequestUpdateDatabase(ObjectNode objectNode) {
        if (!objectNode.hasNonNull("title")) objectNode.remove("title");
    }

    public static void prepareCreateProperties(ObjectNode objectNode) {
        for (JsonNode jsonNode : objectNode.get("properties")) prepareCreateProperty((ObjectNode) jsonNode);
    }

    public static void prepareCreateProperty(ObjectNode property) {

        if (!property.hasNonNull("id")) property.remove("id");

        switch (property.get("type").asText()) {
            case "multi_select":
                prepareCreateOptions((ArrayNode) property.get("multi_select").get("options"));
                break;
            case "select":
                prepareCreateOptions((ArrayNode) property.get("select").get("options"));
                break;
            case "relation":
                ObjectNode objectNode = (ObjectNode) property.get("relation");
                if (!objectNode.hasNonNull("synced_property_name")) objectNode.remove("synced_property_name");
                if (!objectNode.hasNonNull("synced_property_id")) objectNode.remove("synced_property_id");
                break;
            default:
                break;
        }
    }

    public static void prepareCreateOptions(ArrayNode options) {
        for (JsonNode jsonNode : options) {
            ObjectNode option = (ObjectNode) jsonNode;
            option.remove("id");
        }
    }
}
