package tw.yukina.notion.sdk.client;


import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.page.PageModel;

public interface NotionSession extends AutoCloseable {

    PageModel getPageByUuid(String uuid);

    DatabaseModel getDatabaseByUuid(String uuid);

//    Block getBlockByUuid(String uuid);

    PageModel save(PageModel pageModel);

//    Page save(Page page, List<Block> content);

    DatabaseModel save(DatabaseModel databaseModel);

    void flush();

    void close();
}
