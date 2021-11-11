package tw.yukina.notion.sdk.client;


import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.page.Page;

public interface NotionSession extends AutoCloseable {

    Page getPageByUuid(String uuid);

    Database getDatabaseByUuid(String uuid);

//    Page save(Page page);
//
//    Page save(Database database);

    void flush();

    void close();
}
