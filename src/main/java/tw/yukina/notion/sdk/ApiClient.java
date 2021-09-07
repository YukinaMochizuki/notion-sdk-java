package tw.yukina.notion.sdk;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.block.AppendBlockChildren;
import tw.yukina.notion.sdk.endpoint.block.RetrieveBlock;
import tw.yukina.notion.sdk.endpoint.block.RetrieveBlockChildren;
import tw.yukina.notion.sdk.endpoint.block.UpdateBlock;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.deserializer.ZonedDateTimeDeserializer;
import tw.yukina.notion.sdk.model.endpoint.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.ResponseBlockList;
import tw.yukina.notion.sdk.model.serializer.ZonedDateTimeSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiClient {

    private OkHttpClient okHttpClient;

    private Request.Builder requestBuilder;

    private ObjectMapper objectMapper;

    private SimpleModule objectMapperModule;

    public ApiClient(String token){
        setUp(token);
    }

    private void setUp(String token){
        okHttpClient = new OkHttpClient();
        requestBuilder = new Request.Builder()
                .addHeader("Authorization", token)
                .addHeader("Notion-Version", "2021-08-16");

        objectMapperModule = new SimpleModule();
        objectMapperModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        objectMapperModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(getObjectMapperModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList) throws IOException, NotionAPIException {
        return AppendBlockChildren.callValue(uuid, requestChildrenBlockList, okHttpClient, requestBuilder, objectMapper);
    }

    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull JsonNode json) throws IOException, NotionAPIException {
        return AppendBlockChildren.callValue(uuid, json, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode appendBlockChildrenTree(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList) throws IOException, NotionAPIException {
        return AppendBlockChildren.callTree(uuid, requestChildrenBlockList, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode appendBlockChildrenTree(@NotNull String uuid, @NotNull JsonNode json) throws IOException, NotionAPIException {
        return AppendBlockChildren.callTree(uuid, json, okHttpClient, requestBuilder, objectMapper);
    }

    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid) throws IOException, NotionAPIException {
        return RetrieveBlockChildren.callValue(uuid, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode retrieveBlockChildrenTree(@NotNull String uuid) throws IOException, NotionAPIException {
        return RetrieveBlockChildren.callTree(uuid, okHttpClient, requestBuilder, objectMapper);
    }

    public Block updateBlock(@NotNull String uuid, @NotNull Block block) throws IOException, NotionAPIException {
        return UpdateBlock.callValue(uuid, block, okHttpClient, requestBuilder, objectMapper);
    }

    public Block updateBlock(@NotNull String uuid, @NotNull JsonNode json) throws IOException, NotionAPIException {
        return UpdateBlock.callValue(uuid, json, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode updateBlockTree(@NotNull String uuid, @NotNull Block block) throws IOException, NotionAPIException {
        return UpdateBlock.callTree(uuid, block, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode updateBlockTree(@NotNull String uuid, @NotNull JsonNode json) throws IOException, NotionAPIException {
        return UpdateBlock.callTree(uuid, json, okHttpClient, requestBuilder, objectMapper);
    }

    public Block retrieveBlock(@NotNull String uuid) throws IOException, NotionAPIException {
        return RetrieveBlock.callValue(uuid, okHttpClient, requestBuilder, objectMapper);
    }

    public ObjectNode retrieveBlockTree(@NotNull String uuid) throws IOException, NotionAPIException {
        return RetrieveBlock.callTree(uuid, okHttpClient, requestBuilder, objectMapper);
    }
}
