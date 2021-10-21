package tw.yukina.notion.sdk.client;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.endpoint.block.RequestChildrenBlockList;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;

public interface ApiClient {

    Block retrieveBlock(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid);

    ResponseBlockList retrieveBlockChildren(@NotNull String uuid, @NotNull String startCursor);

    ResponseBlockList appendBlockChildren(@NotNull String uuid, @NotNull RequestChildrenBlockList requestChildrenBlockList);

    Block updateBlock(@NotNull String uuid, Block block);

    Block deleteBlock(@NotNull String uuid);

}
