package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.AbstractEndpoint;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.endpoint.ResponseBlockList;

public abstract class AbstractBlockEndpoint extends AbstractEndpoint {

    public static final String PATH = "/blocks/";

    public static Block toBlock(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(objectNode.toString(), Block.class);
    }

    public static ResponseBlockList toBlockList(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) throws JsonProcessingException {
        return objectMapper.readValue(objectNode.toString(), ResponseBlockList.class);
    }

    public static void preparePostBlock(@NotNull ObjectNode block){
        if(!block.hasNonNull("id"))block.remove("id");
        block.remove("created_time");
        block.remove("last_edited_time");
    }
}
