package tw.yukina.notion.sdk.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.endpoint.search.RequestSearch;
import tw.yukina.notion.sdk.model.endpoint.search.ResponseObjectList;


public class Search extends AbstractEndpoint {

    public static final String PATH = "/search/";

    @NotNull
    public static ResponseObjectList callValue(@NotNull RequestSearch requestSearch,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) {

        return toObjectList(callTree(requestSearch, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponseObjectList callValue(@NotNull JsonNode json,
                                 @NotNull OkHttpClient okHttpClient,
                                 @NotNull Request.Builder builder,
                                 @NotNull ObjectMapper objectMapper) {

        return toObjectList(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ResponseObjectList callValue(@NotNull String json,
                                               @NotNull OkHttpClient okHttpClient,
                                               @NotNull Request.Builder builder,
                                               @NotNull ObjectMapper objectMapper) {

        return toObjectList(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull RequestSearch requestSearch,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(requestSearch, okHttpClient, builder, objectMapper), objectMapper);
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
    public static Response call(@NotNull RequestSearch requestSearch,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(objectMapper.valueToTree(requestSearch), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);
        preparePostSearch(objectNode);

        return call(objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        return getResponse(json, okHttpClient, builder, PATH);

    }

    public static ResponseObjectList toObjectList(@NotNull ObjectNode objectNode, @NotNull ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(objectNode.toString(), ResponseObjectList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void preparePostSearch(@NotNull ObjectNode requestSearch){
        if(!requestSearch.hasNonNull("query"))requestSearch.remove("query");
        if(!requestSearch.hasNonNull("sort"))requestSearch.remove("sort");
        if(!requestSearch.hasNonNull("filter"))requestSearch.remove("filter");
        if(!requestSearch.hasNonNull("start_cursor"))requestSearch.remove("start_cursor");
    }
}