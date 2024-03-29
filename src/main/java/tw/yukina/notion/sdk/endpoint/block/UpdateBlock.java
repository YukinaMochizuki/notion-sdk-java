package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.BlockModel;

public class UpdateBlock extends AbstractBlockEndpoint {

    @NotNull
    public static BlockModel callValue(@NotNull String uuid, @NotNull BlockModel block,
                                       @NotNull OkHttpClient okHttpClient,
                                       @NotNull Request.Builder builder,
                                       @NotNull ObjectMapper objectMapper) {

        return toBlock(callTree(uuid, block, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static BlockModel callValue(@NotNull String uuid, @NotNull JsonNode json,
                                       @NotNull OkHttpClient okHttpClient,
                                       @NotNull Request.Builder builder,
                                       @NotNull ObjectMapper objectMapper) {

        return toBlock(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static BlockModel callValue(@NotNull String uuid, @NotNull String json,
                                       @NotNull OkHttpClient okHttpClient,
                                       @NotNull Request.Builder builder,
                                       @NotNull ObjectMapper objectMapper) {

        return toBlock(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull BlockModel block,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, block, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull String json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull BlockModel block,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(uuid, objectMapper.valueToTree(block), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);
        preparePostBlock(objectNode);

        return call(uuid, objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        return getResponse(uuid, json, okHttpClient, builder, PATH);
    }
}
