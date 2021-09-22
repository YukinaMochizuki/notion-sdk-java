package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.endpoint.block.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;
import static tw.yukina.notion.sdk.Configuration.MEDIA_TYPE_JSON;

public class AppendBlockChildren extends AbstractBlockEndpoint {

    @NotNull
    public static ResponseBlockList callValue(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toBlockList(callTree(uuid, requestChildrenBlockList, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponseBlockList callValue(@NotNull String uuid, @NotNull JsonNode json,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toBlockList(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponseBlockList callValue(@NotNull String uuid, @NotNull String json,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toBlockList(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(uuid, requestChildrenBlockList, okHttpClient, builder, objectMapper), objectMapper);
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
    public static Response call(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) throws IOException {

        return call(uuid, objectMapper.valueToTree(requestChildrenBlockList), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        ObjectNode objectNode = getObjectNode(json);
        ArrayNode arrayNode = (ArrayNode) objectNode.get("children");
        for(JsonNode jsonNode : arrayNode) preparePostBlock((ObjectNode) jsonNode);

        return call(uuid, objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + PATH + uuid + "/children").patch(body).build();
        Call call = okHttpClient.newCall(request);

        return call.execute();
    }
}
