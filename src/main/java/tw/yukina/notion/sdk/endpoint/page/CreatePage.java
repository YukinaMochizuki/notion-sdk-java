package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.page.Page;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;
import static tw.yukina.notion.sdk.Configuration.MEDIA_TYPE_JSON;

public class CreatePage extends AbstractPageEndpoint {

    @NotNull
    public static Page callValue(@NotNull RequestCreatePage requestCreatePage,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(requestCreatePage, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static Page callValue(@NotNull JsonNode json,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static Page callValue(@NotNull String json,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull RequestCreatePage requestCreatePage,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(requestCreatePage, okHttpClient, builder, objectMapper), objectMapper);
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
    public static Response call(@NotNull RequestCreatePage requestCreatePage,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(objectMapper.valueToTree(requestCreatePage), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);

        if(objectNode.has("children") && objectNode.get("children").isArray())
            preparePostBlocks((ArrayNode) objectNode.get("children"));

        return call(objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        return getResponse(json, okHttpClient, builder, PATH);
    }
}
