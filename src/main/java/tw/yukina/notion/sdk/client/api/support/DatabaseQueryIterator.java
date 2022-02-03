package tw.yukina.notion.sdk.client.api.support;

import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.ResponsePageList;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.Iterator;

public class DatabaseQueryIterator implements Iterator<Page> {

    private final DatabaseQueryCollector collector;

    private ResponsePageList responsePageList;

    private int index = 0;

    public DatabaseQueryIterator(DatabaseQueryCollector collector){
        this.collector = collector;
    }

    @Override
    public boolean hasNext() {
        if(responsePageList == null){
            responsePageList = collector.apiClient.queryDatabase(collector.uuid, collector.databaseQuery);
        }
        return index < responsePageList.getPages().size() || responsePageList.isHasMore();
    }

    @Override
    public Page next() {
        if(index < responsePageList.getPages().size()){
            Page page = responsePageList.getPages().get(index);
            index += 1;
            return page;
        } else if(responsePageList.isHasMore()){
            DatabaseQuery databaseQuery = new DatabaseQuery();
            databaseQuery.setCompound(collector.databaseQuery.getCompound());
            databaseQuery.setSorts(collector.databaseQuery.getSorts());
            databaseQuery.setPageSize(collector.databaseQuery.getPageSize());
            databaseQuery.setStartCursor(responsePageList.getNextCursor());

            this.responsePageList = collector.apiClient.queryDatabase(collector.uuid, databaseQuery);
            System.out.println("new call");
            index = 0;
            if(index < responsePageList.getPages().size()) {
                Page page = responsePageList.getPages().get(index);
                index += 1;
                return page;
            }
        }

        return null;
    }
}
