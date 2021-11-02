package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.block.AbstractBlockEndpoint;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.*;

public abstract class AbstractPageEndpoint extends AbstractBlockEndpoint {

    public static final String PATH = "/pages/";

    public static Page toPage(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), Page.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void preparePageRequest(ObjectNode objectNode){
        ObjectNode properties = (ObjectNode) objectNode.get("properties");
        Iterator<Map.Entry<String, JsonNode>> nodes = properties.fields();

        List<String> propertyNeedRemove = new ArrayList<>();

        while (nodes.hasNext()) {
            Map.Entry<String, JsonNode> entry = nodes.next();
            if(propertyRequestNeedRemove(entry)) propertyNeedRemove.add(entry.getKey());
        }

        for(String propertyName: propertyNeedRemove){
            properties.remove(propertyName);
        }
    }

    public static boolean propertyRequestNeedRemove(Map.Entry<String, JsonNode> entry){
        switch (entry.getValue().get("type").asText()) {
            case "formula":
            case "rollup":
            case "created_time":
            case "created_by":
            case "last_edited_time":
            case "last_edited_by":
                return true;
            case "select":
                return !entry.getValue().has("select");
            case "url":
                return !entry.getValue().has("url");
            case "phone_number":
                return !entry.getValue().has("phone_number");
            case "email":
                return !entry.getValue().has("email");
            case "date":
                return !entry.getValue().has("date");
            // TODO: 11/2/21  
        }
        return false;
    }
}
