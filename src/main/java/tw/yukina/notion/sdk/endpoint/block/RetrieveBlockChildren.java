package tw.yukina.notion.sdk.endpoint.block;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.endpoint.ResponseBlockList;

import java.io.IOException;

import static tw.yukina.notion.sdk.Configuration.BASE_URL;

public class RetrieveBlockChildren extends AbstractBlockEndpoint {

    public static ResponseBlockList callValue(@NotNull String uuid,
                                              @NotNull OkHttpClient okHttpClient,
                                              @NotNull Request.Builder builder,
                                              @NotNull ObjectMapper objectMapper) throws IOException, NotionAPIException {

        ObjectNode objectNode = callTree(uuid, okHttpClient, builder, objectMapper);
        return toBlockList(objectNode, objectMapper);
    }

    @NotNull
    public static ObjectNode callTree(@NotNull String uuid,
                                      @NotNull OkHttpClient okHttpClient,
                                      @NotNull Request.Builder builder,
                                      @NotNull ObjectMapper objectMapper) throws NotionAPIException, IOException {

        return getObjectNode(call(uuid, okHttpClient, builder), objectMapper);
    }

    @NotNull
    public static Response call(@NotNull String uuid,
                                @NotNull OkHttpClient okHttpClient,
                                @NotNull Request.Builder builder) throws IOException {

        okhttp3.Request request = builder.url(BASE_URL + PATH + uuid + "/children").build();
        Call call = okHttpClient.newCall(request);

        return call.execute();
    }
}