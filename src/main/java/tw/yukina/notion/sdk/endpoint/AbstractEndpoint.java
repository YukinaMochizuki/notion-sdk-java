package tw.yukina.notion.sdk.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Response;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractEndpoint {

    @NotNull
    public static ObjectNode getObjectNode(@NotNull Response response, @NotNull ObjectMapper objectMapper)
            throws IOException, NotionAPIException {
        return checkJsonErrorAndGetObjectNode(objectMapper.readTree(Objects.requireNonNull(response.body()).charStream()));
    }

    @NotNull
    @Contract(pure = true)
    public static ObjectNode getObjectNode(@NotNull JsonNode jsonNode) throws IOException {
        ObjectNode objectNode;

        if(jsonNode instanceof ObjectNode){
            objectNode = (ObjectNode) jsonNode;
        } else throw new IOException("is not object");

        return objectNode;
    }

    @NotNull
    public static ObjectNode checkJsonErrorAndGetObjectNode(@NotNull JsonNode jsonNode) throws NotionAPIException {
        ObjectNode objectNode;

        if(jsonNode instanceof ObjectNode){
            objectNode = (ObjectNode) jsonNode;
        } else throw new NotionAPIException("Unexpected error, body is not object");
        if(objectNode.get("object").asText().equals("error")) throw new NotionAPIException(objectNode);

        return objectNode;
    }
}
