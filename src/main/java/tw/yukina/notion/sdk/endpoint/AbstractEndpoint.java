package tw.yukina.notion.sdk.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.endpoint.exception.UnsupportedJsonFormatException;

import java.io.IOException;
import java.util.Objects;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;
import static tw.yukina.notion.sdk.Configuration.MEDIA_TYPE_JSON;

public abstract class AbstractEndpoint {

    @NotNull
    public static ObjectNode getObjectNode(@NotNull Response response, @NotNull ObjectMapper objectMapper) {
        ObjectNode objectNode = null;
        try {
            objectNode = checkJsonErrorAndGetObjectNode(objectMapper.readTree(Objects
                    .requireNonNull(response.body()).charStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        response.close();

        return objectNode;
    }

    @NotNull
    @Contract(pure = true)
    public static ObjectNode getObjectNode(@NotNull JsonNode jsonNode) {
        ObjectNode objectNode;

        if (jsonNode instanceof ObjectNode) {
            objectNode = (ObjectNode) jsonNode;
        } else throw new UnsupportedJsonFormatException(jsonNode);

        return objectNode;
    }

    @NotNull
    public static ObjectNode checkJsonErrorAndGetObjectNode(@NotNull JsonNode jsonNode) {
        ObjectNode objectNode;

        if (jsonNode instanceof ObjectNode) {
            objectNode = (ObjectNode) jsonNode;
        } else throw new NotionAPIException("Unexpected error, body is not object");
        if (objectNode.get("object").asText().equals("error")) throw new NotionAPIException(objectNode);

        return objectNode;
    }

    @NotNull
    public static Response getResponse(@NotNull String uuid, @NotNull String json,
                                       @NotNull OkHttpClient okHttpClient,
                                       @NotNull Request.Builder builder,
                                       String path) {

        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + path + uuid).patch(body).build();
        Call call = okHttpClient.newCall(request);

        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static Response getResponse(@NotNull String json, @NotNull OkHttpClient okHttpClient,
                                       @NotNull Request.Builder builder,
                                       String path) {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE_JSON);
        Request request = builder.url(BASE_URL + path).post(body).build();
        Call call = okHttpClient.newCall(request);

        try {
            return call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
