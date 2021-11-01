package tw.yukina.notion.sdk.client;


import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.page.Page;

public interface NotionClient {

    Page getPageByUuid(String uuid);

    Database getDatabaseByUuid(String uuid);

    void flush();

}
