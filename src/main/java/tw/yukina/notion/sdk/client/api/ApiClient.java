package tw.yukina.notion.sdk.client.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.support.Pages;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.block.RequestAppendChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

public interface ApiClient {

    Block retrieveBlock(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestAppendChildrenBlockList requestAppendChildrenBlockList);

    Block updateBlock(@NotNull String uuid, Block block);

    Block deleteBlock(@NotNull String uuid);

    Database createDatabase(@NotNull RequestCreateDatabase requestCreateDatabase);

    ResponsePageList queryDatabase(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery);

    Pages queryDatabaseIterable(@NotNull String uuid, @NotNull DatabaseQuery databaseQuery);

    Database retrieveDatabase(@NotNull String uuid);

    Database updateDatabase(@NotNull String uuid, @NotNull RequestUpdateDatabase requestUpdateDatabase);

    Page createPage(@NotNull RequestCreatePage requestCreatePage);

    Page retrievePage(@NotNull String uuid);

    Page updatePage(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage);

    <T extends JsonNode> T serialize(Object fromValue);
}
