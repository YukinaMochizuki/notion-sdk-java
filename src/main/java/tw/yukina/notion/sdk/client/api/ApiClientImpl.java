package tw.yukina.notion.sdk.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.support.DatabaseQueryCollector;
import tw.yukina.notion.sdk.client.api.support.PageModels;
import tw.yukina.notion.sdk.endpoint.block.*;
import tw.yukina.notion.sdk.endpoint.database.CreateDatabase;
import tw.yukina.notion.sdk.endpoint.database.QueryDatabase;
import tw.yukina.notion.sdk.endpoint.database.RetrieveDatabase;
import tw.yukina.notion.sdk.endpoint.database.UpdateDatabase;
import tw.yukina.notion.sdk.endpoint.page.CreatePage;
import tw.yukina.notion.sdk.endpoint.page.RetrievePage;
import tw.yukina.notion.sdk.endpoint.page.UpdatePage;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.PageModel;

import java.util.List;

@Getter
@Setter
public class ApiClientImpl implements ApiClient {

    @Getter(AccessLevel.PRIVATE)
    private final String authorization;
    private OkHttpClient okHttpClient;
    private ObjectMapper objectMapper;
    private SimpleModule objectMapperModule;
    private String notionVersion;

    public ApiClientImpl(String token) {
        this.authorization = token;
    }

    @NotNull
    private Request.Builder getRequestBuilder() {
        return new Request.Builder()
                .addHeader("Authorization", this.authorization)
                .addHeader("Notion-Version", this.notionVersion);
    }

    @Override
    public BlockModel retrieveBlock(@NotNull String uuid) {
        return RetrieveBlock.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid) {
        return RetrieveBlockChildren.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor) {
        return RetrieveBlockChildren.callValue(uuid, getOkHttpClient(), getRequestBuilder(),
                getObjectMapper(), startCursor);
    }

    @Override
    public ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor,
                                                   @NotNull Integer pageSize) {
        return RetrieveBlockChildren.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper(),
                startCursor, pageSize);
    }

    @Override
    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull BlockModel block) {
        List<BlockModel> blocks = List.of(block);
        return appendBlockChildren(uuid, blocks);
    }

    @Override
    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull BlockModel... blocks) {
        List<BlockModel> blockList = List.of(blocks);
        return appendBlockChildren(uuid, blockList);
    }

    @Override
    public ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull List<? extends BlockModel> blocks) {
        RequestAppendChildrenBlockList requestAppendChildrenBlockList = new RequestAppendChildrenBlockList(blocks);
        return appendBlockChildren(uuid, requestAppendChildrenBlockList);
    }

    @Override
    public ResponseBlockList appendBlockChildren(@NotNull String uuid,
                                                 @NotNull RequestAppendChildrenBlockList
                                                         requestAppendChildrenBlockList) {
        return AppendBlockChildren.callValue(uuid, requestAppendChildrenBlockList, getOkHttpClient(),
                getRequestBuilder(), getObjectMapper());
    }

    @Override
    public BlockModel updateBlock(@NotNull String uuid, BlockModel block) {
        return UpdateBlock.callValue(uuid, block, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public BlockModel deleteBlock(@NotNull String uuid) {
        return DeleteBlock.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public DatabaseModel createDatabase(@NotNull RequestCreateDatabase requestCreateDatabase) {
        return CreateDatabase.callValue(requestCreateDatabase, getOkHttpClient(),
                getRequestBuilder(), getObjectMapper());
    }

    @Override
    public ResponsePageList queryDatabase(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery) {
        return QueryDatabase.callValue(uuid, databaseQuery, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public PageModels queryDatabaseIterable(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery) {
        return new DatabaseQueryCollector(uuid, databaseQuery, this);
    }

    @Override
    public DatabaseModel retrieveDatabase(@NotNull String uuid) {
        return RetrieveDatabase.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public DatabaseModel updateDatabase(@NotNull String uuid, @NotNull RequestUpdateDatabase requestUpdateDatabase) {
        return UpdateDatabase.callValue(uuid, requestUpdateDatabase, getOkHttpClient(),
                getRequestBuilder(), getObjectMapper());
    }

    @Override
    public PageModel createPage(@NotNull RequestCreatePage requestCreatePage) {
        return CreatePage.callValue(requestCreatePage, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public PageModel retrievePage(@NotNull String uuid) {
        return RetrievePage.callValue(uuid, getOkHttpClient(), getRequestBuilder(), getObjectMapper());
    }

    @Override
    public PageModel updatePage(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage) {
        return UpdatePage.callValue(uuid, requestUpdatePage, getOkHttpClient(),
                getRequestBuilder(), getObjectMapper());
    }

    @Override
    public <T extends JsonNode> T serialize(Object fromValue) {
        return getObjectMapper().valueToTree(fromValue);
    }
}
