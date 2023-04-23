package tw.yukina.notion.sdk.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.support.Pages;
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

public interface ApiClient {

    BlockModel retrieveBlock(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull BlockModel block);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull BlockModel... blocks);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull List<BlockModel> blocks);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestAppendChildrenBlockList requestAppendChildrenBlockList);

    BlockModel updateBlock(@NotNull String uuid, BlockModel block);

    BlockModel deleteBlock(@NotNull String uuid);

    DatabaseModel createDatabase(@NotNull RequestCreateDatabase requestCreateDatabase);

    ResponsePageList queryDatabase(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery);

    Pages queryDatabaseIterable(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery);

    DatabaseModel retrieveDatabase(@NotNull String uuid);

    DatabaseModel updateDatabase(@NotNull String uuid, @NotNull RequestUpdateDatabase requestUpdateDatabase);

    PageModel createPage(@NotNull RequestCreatePage requestCreatePage);

    PageModel retrievePage(@NotNull String uuid);

    PageModel updatePage(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage);

    <T extends JsonNode> T serialize(Object fromValue);
}
