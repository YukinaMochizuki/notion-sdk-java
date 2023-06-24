package tw.yukina.notion.sdk.client.api.support;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.page.PageModel;

import java.util.Iterator;

public class DatabaseQueryCollector extends PageModels {

    protected final String uuid;

    protected final DatabaseQuery databaseQuery;

    protected final ApiClient apiClient;

    public DatabaseQueryCollector(String uuid, DatabaseQuery databaseQuery, ApiClient apiClient) {
        this.uuid = uuid;
        this.databaseQuery = databaseQuery;
        this.apiClient = apiClient;
    }

    @NotNull
    @Override
    public Iterator<PageModel> iterator() {
        return new DatabaseQueryIterator(this);
    }
}
