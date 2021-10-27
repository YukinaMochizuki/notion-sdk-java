package tw.yukina.notion.sdk.client;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.database.QueryDatabase;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.block.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

public interface ApiClient {

    Block retrieveBlock(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList);

    Block updateBlock(@NotNull String uuid, Block block);

    Block deleteBlock(@NotNull String uuid);

    Database CreateDatabase(@NotNull RequestCreateDatabase requestCreateDatabase);

    ResponsePageList QueryDatabase(@NotNull String uuid, @NotNull QueryDatabase queryDatabase);

    Database RetrieveDatabase(@NotNull String uuid);

    Database UpdateDatabase(@NotNull String uuid, @NotNull RequestUpdateDatabase requestUpdateDatabase);

    Page CreatePage(@NotNull RequestCreatePage requestCreatePage);

    Page RetrievePage(@NotNull String uuid);

    Page UpdatePage(@NotNull String uuid, @NotNull RequestUpdatePage requestUpdatePage);
}
