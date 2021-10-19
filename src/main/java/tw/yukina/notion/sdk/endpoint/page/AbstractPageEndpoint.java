package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.block.AbstractBlockEndpoint;
import tw.yukina.notion.sdk.model.page.Page;

public abstract class AbstractPageEndpoint extends AbstractBlockEndpoint {

    public static final String PATH = "/pages/";

    public static Page toPage(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), Page.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
