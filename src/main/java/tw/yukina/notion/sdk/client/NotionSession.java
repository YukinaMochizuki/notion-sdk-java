package tw.yukina.notion.sdk.client;


import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.List;

public interface NotionSession extends AutoCloseable {

    Page getPageByUuid(String uuid);

    Database getDatabaseByUuid(String uuid);

//    Block getBlockByUuid(String uuid);

    Page save(Page page);

//    Page save(Page page, List<Block> content);

    Database save(Database database);

    void flush();

    void close();
}
