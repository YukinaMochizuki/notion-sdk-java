package tw.yukina.notion.sdk.endpoint.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;

public class CreateDatabase extends AbstractDatabaseEndpoint {

    @NotNull
    public static DatabaseModel callValue(@NotNull RequestCreateDatabase requestCreateDatabase,
                                          @NotNull OkHttpClient okHttpClient,
                                          @NotNull Request.Builder builder,
                                          @NotNull ObjectMapper objectMapper) {

        return toDatabase(callTree(requestCreateDatabase, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static DatabaseModel callValue(@NotNull JsonNode json,
                                          @NotNull OkHttpClient okHttpClient,
                                          @NotNull Request.Builder builder,
                                          @NotNull ObjectMapper objectMapper) {

        return toDatabase(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static DatabaseModel callValue(@NotNull String json,
                                          @NotNull OkHttpClient okHttpClient,
                                          @NotNull Request.Builder builder,
                                          @NotNull ObjectMapper objectMapper) {

        return toDatabase(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull RequestCreateDatabase requestCreateDatabase,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(requestCreateDatabase, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(json, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull RequestCreateDatabase requestCreateDatabase,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(objectMapper.valueToTree(requestCreateDatabase), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);
        prepareCreateProperties(objectNode);

        return call(objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        return getResponse(json, okHttpClient, builder, PATH);
    }
}
