package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.AbstractEndpoint;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

public abstract class AbstractBlockEndpoint extends AbstractEndpoint {

    public static final String PATH = "/blocks/";

    public static BlockModel toBlock(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), BlockModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponseBlockList toBlockList(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), ResponseBlockList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void preparePostBlocks(@NotNull ArrayNode arrayNode) {
        for (JsonNode jsonNode : arrayNode) {
            if (jsonNode.isObject()) {
                preparePostBlock((ObjectNode) jsonNode);
            }
        }
    }

    public static void preparePostBlock(@NotNull ObjectNode block) {
        if (!block.hasNonNull("id")) block.remove("id");
        block.remove("created_time");
        block.remove("last_edited_time");
    }
}
