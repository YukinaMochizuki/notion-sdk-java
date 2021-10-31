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
import tw.yukina.notion.sdk.endpoint.database.CreateDatabase;
import tw.yukina.notion.sdk.endpoint.database.QueryDatabase;
import tw.yukina.notion.sdk.endpoint.database.RetrieveDatabase;
import tw.yukina.notion.sdk.endpoint.database.UpdateDatabase;
import tw.yukina.notion.sdk.endpoint.page.CreatePage;
import tw.yukina.notion.sdk.endpoint.page.RetrievePage;
import tw.yukina.notion.sdk.endpoint.page.UpdatePage;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

@Getter
@Setter
public class ApiClientImpl implements ApiClient{

    private OkHttpClient okHttpClient;

    private ObjectMapper objectMapper;

    private SimpleModule objectMapperModule;

    @Getter(AccessLevel.PRIVATE)
    private final String authorization;

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
    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestAppendChildrenBlockList requestAppendChildrenBlockList) {
        return AppendBlockChildren.callValue(uuid, requestAppendChildrenBlockList, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Block updateBlock(@NotNull String uuid, Block block) {
        return UpdateBlock.callValue(uuid, block, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Block deleteBlock(@NotNull String uuid) {
        return DeleteBlock.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Database CreateDatabase(@NotNull RequestCreateDatabase requestCreateDatabase) {
        return CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponsePageList QueryDatabase(@NotNull String uuid, @NotNull QueryDatabase queryDatabase) {
        return QueryDatabase.callValue(uuid, queryDatabase, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Database RetrieveDatabase(@NotNull String uuid) {
        return RetrieveDatabase.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Database UpdateDatabase(@NotNull String uuid, @NotNull RequestUpdateDatabase requestUpdateDatabase) {
        return UpdateDatabase.callValue(uuid, requestUpdateDatabase, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Page CreatePage(@NotNull RequestCreatePage requestCreatePage) {
        return CreatePage.callValue(requestCreatePage, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Page RetrievePage(@NotNull String uuid) {
        return RetrievePage.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public Page UpdatePage(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage) {
        return UpdatePage.callValue(uuid, requestUpdatePage, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }
}
