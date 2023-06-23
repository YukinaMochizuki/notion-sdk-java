package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

import java.io.IOException;
import java.util.Objects;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;

public class RetrieveBlockChildren extends AbstractBlockEndpoint {

    public static final int DEFAULT_PAGE_SIZE = 100;

    public static ResponseBlockList callValue(@NotNull String uuid,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper) {

        ObjectNode objectNode = callTree(uuid, okHttpClient, builder, objectMapper);
        return toBlockList(objectNode, objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) {

        return getObjectNode(call(uuid, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull String uuid,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) {

        okhttp3.Request request = builder.url(BASE_URL + PATH + uuid + "/children").build();
        Call call = okHttpClient.newCall(request);

        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResponseBlockList callValue(@NotNull String uuid,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper,
                                              @NotNull String startCursor) {

        ObjectNode objectNode = callTree(uuid, okHttpClient, builder, objectMapper, startCursor);
        return toBlockList(objectNode, objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper,
                                      @NotNull String startCursor) {

        return getObjectNode(call(uuid, okHttpClient, builder, startCursor, DEFAULT_PAGE_SIZE), objectMapper);
    }

    @NotNull
    public static ResponseBlockList callValue(@NotNull String uuid,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper,
                                              @NotNull String startCursor, int pageSize) {

        ObjectNode objectNode = callTree(uuid, okHttpClient, builder, objectMapper, startCursor, pageSize);
        return toBlockList(objectNode, objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper,
                                      @NotNull String startCursor, int pageSize) {

        return getObjectNode(call(uuid, okHttpClient, builder, startCursor, pageSize), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull String uuid,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder,
                                @NotNull String startCursor, int pageSize) {

        var urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL + PATH + uuid + "/children")).newBuilder()
                .addQueryParameter("page_size", String.valueOf(pageSize));

        if (!startCursor.equals("")) {
            urlBuilder.addQueryParameter("start_cursor", startCursor);
        }

        okhttp3.Request request = builder.url(urlBuilder.build()).build();
        Call call = okHttpClient.newCall(request);

        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
