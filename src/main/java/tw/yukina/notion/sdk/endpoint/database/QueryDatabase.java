package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;
import static tw.yukina.notion.sdk.Configuration.MEDIA_TYPE_JSON;

public class QueryDatabase extends AbstractDatabaseEndpoint {

    @NotNull
    public static ResponsePageList callValue(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery,
                                             @NotNull OkHttpClient okHttpClient,
                                             @NotNull Request.Builder builder,
                                             @NotNull ObjectMapper objectMapper) {

        return toPageList(callTree(uuid, databaseQuery, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponsePageList callValue(@NotNull String uuid, @NotNull JsonNode json,
                                             @NotNull OkHttpClient okHttpClient,
                                             @NotNull Request.Builder builder,
                                             @NotNull ObjectMapper objectMapper) {

        return toPageList(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponsePageList callValue(@NotNull String uuid, @NotNull String json,
                                             @NotNull OkHttpClient okHttpClient,
                                             @NotNull Request.Builder builder,
                                             @NotNull ObjectMapper objectMapper) {

        return toPageList(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, databaseQuery, okHttpClient, builder, objectMapper), objectMapper);
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
    public static Response call(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(uuid, objectMapper.valueToTree(databaseQuery), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid,
                                @NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);
        return call(uuid, objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid,
                                @NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + PATH + uuid + "/query").post(body).build();
        Call call = okHttpClient.newCall(request);

        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponsePageList toPageList(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), ResponsePageList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
