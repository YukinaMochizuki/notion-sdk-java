package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.PageModel;

public class UpdatePage extends AbstractPageEndpoint {

    @NotNull
    public static PageModel callValue(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(uuid, requestUpdatePage, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static PageModel callValue(@NotNull String uuid, @NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static PageModel callValue(@NotNull String uuid, @NotNull String json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(uuid, json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, requestUpdatePage, okHttpClient, builder, objectMapper), objectMapper);
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
    public static Response call(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull ObjectMapper objectMapper) {

        return call(uuid, objectMapper.valueToTree(requestUpdatePage), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull JsonNode json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        ObjectNode objectNode = getObjectNode(json);
        preparePageRequest(objectNode);

        return call(uuid, objectNode.toString(), okHttpClient, builder);
    }

    @NotNull
    public static Response call(@NotNull String uuid, @NotNull String json,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        return getResponse(uuid, json, okHttpClient, builder, PATH);
    }
}
