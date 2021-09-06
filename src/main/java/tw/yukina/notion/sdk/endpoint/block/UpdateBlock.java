package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.block.Block;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.*;

public class UpdateBlock extends AbstractBlockEndpoint{

    @NotNull
    public static Block callValue(@NotNull String uuid, @NotNull Block block,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        ObjectNode objectNode = getObjectNode(call(uuid, block, okHttpClient, builder, objectMapper), objectMapper);
        return toBlock(objectNode, objectMapper);
    }

    @NotNull
    public static Block callValue(@NotNull String uuid, @NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        ObjectNode objectNode = getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
        return toBlock(objectNode, objectMapper);
    }

    @NotNull
    public static Block callValue(@NotNull String uuid, @NotNull String json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        ObjectNode objectNode = getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
        return toBlock(objectNode, objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull Block block,
                                         @NotNull OkHttpClient okHttpClient,
                                         @NotNull Request.Builder builder,
                                         @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(uuid, block, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull JsonNode json,
                                         @NotNull OkHttpClient okHttpClient,
                                         @NotNull Request.Builder builder,
                                         @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull String json,
                                         @NotNull OkHttpClient okHttpClient,
                                         @NotNull Request.Builder builder,
                                         @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(uuid, json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull Block block,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) throws IOException {

        return call(uuid, objectMapper.valueToTree(block), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        ObjectNode objectNode = getObjectNode(json);
        preparePostBlock(objectNode);

        return call(uuid, objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + PATH + uuid).patch(body).build();
        Call call = okHttpClient.newCall(request);

        return call.execute();
    }
}
