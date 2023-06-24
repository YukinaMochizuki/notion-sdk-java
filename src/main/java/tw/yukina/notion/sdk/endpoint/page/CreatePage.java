package tw.yukina.notion.sdk.endpoint.page;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.page.PageModel;

public class CreatePage extends AbstractPageEndpoint {

    @NotNull
    public static PageModel callValue(@NotNull RequestCreatePage requestCreatePage,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(requestCreatePage, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static PageModel callValue(@NotNull JsonNode json,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return toPage(callTree(json, okHttpClient, builder, objectMapper), objectMapper);
    }

    @NotNull
    public static PageModel callValue(@NotNull String json,
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

        if (objectNode.has("parent") && objectNode.get("parent").isObject()) {
            ObjectNode parent = (ObjectNode) objectNode.get("parent");
            if (parent.has("type")) parent.remove("type");
        }

        if (objectNode.has("children") && objectNode.get("children").isArray())
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
