package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.AbstractEndpoint;
import tw.yukina.notion.sdk.model.page.Page;

public abstract class AbstractPageEndpoint extends AbstractEndpoint {

    public static final String PATH = "/pages/";

    public static Page toPage(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(objectNode.toString(), Page.class);
    }

    public static void preparePostBlocks(@NotNull ArrayNode arrayNode){
        for(JsonNode jsonNode : arrayNode){
            if(jsonNode.isObject()){
                ObjectNode block = (ObjectNode) jsonNode;
                if(!block.hasNonNull("id"))block.remove("id");
                block.remove("created_time");
                block.remove("last_edited_time");
            }
        }
    }

}
