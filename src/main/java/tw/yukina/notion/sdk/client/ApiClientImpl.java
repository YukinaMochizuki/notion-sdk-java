package tw.yukina.notion.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.block.*;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.endpoint.block.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

@Getter
@Setter
public class ApiClientImpl implements ApiClient{

    private OkHttpClient okHttpClient;

    private ObjectMapper objectMapper;

    private SimpleModule objectMapperModule;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PRIVATE)
    private String authorization;

    private String notionVersion;

    public ApiClientImpl(String token){
        this.authorization = token;
    }

    @NotNull
    private Request.Builder getRequestBuilder(){
        return new Request.Builder()
                .addHeader("Authorization", this.authorization)
                .addHeader("Notion-Version", this.notionVersion);
    }

    @Override
    public Block retrieveBlock(@NotNull String uuid) {
        return RetrieveBlock.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid) {
        return RetrieveBlockChildren.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor) {
        return RetrieveBlockChildren.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper(), startCursor);
    }

    @Override
    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList) {
        return AppendBlockChildren.callValue(uuid, requestChildrenBlockList, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Block updateBlock(@NotNull String uuid, Block block) {
        return UpdateBlock.callValue(uuid, block, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Block deleteBlock(@NotNull String uuid) {
        return DeleteBlock.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }
}
