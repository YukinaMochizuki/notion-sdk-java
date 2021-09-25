package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;
import static tw.yukina.notion.sdk.Configuration.MEDIA_TYPE_JSON;

public class CreateDatabase extends AbstractDatabaseEndpoint {

    @NotNull
    public static Database callValue(@NotNull RequestCreateDatabase requestCreateDatabase,
                                     @NotNull OkHttpClient okHttpClient,
                                     @NotNull Request.Builder builder,
                                     @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toDatabase(callTree(requestCreateDatabase, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static Database callValue(@NotNull JsonNode json,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toDatabase(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static Database callValue(@NotNull String json,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return toDatabase(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull RequestCreateDatabase requestCreateDatabase,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(requestCreateDatabase, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        return getObjectNode(call(json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull RequestCreateDatabase requestCreateDatabase,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) throws IOException {

        return call(objectMapper.valueToTree(requestCreateDatabase), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        ObjectNode objectNode = getObjectNode(json);
        prepareCreateProperties(objectNode);

        return call(objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + PATH).post(body).build();
        Call call = okHttpClient.newCall(request);

        return call.execute();
    }
}
