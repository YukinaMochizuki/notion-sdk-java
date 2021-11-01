package tw.yukina.notion.sdk.client;

import lombok.Getter;
import lombok.Setter;
import net.sf.cglib.proxy.Enhancer;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientFactory;
import tw.yukina.notion.sdk.client.entity.Entity;
import tw.yukina.notion.sdk.client.entity.EntitySession;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class NotionClientImpl implements NotionClient {

    private final ApiClient apiClient;

    private final List<EntitySession> entities = new ArrayList<>();

    private final String sessionUuid = UUID.randomUUID().toString();

    public NotionClientImpl(String token){
        ApiClientFactory apiClientFactory = new ApiClientFactory();
        apiClientFactory.setToken(token);
        apiClientFactory.applyDefaultSetting();
        apiClient = apiClientFactory.build();
    }

    @Override
    public Page getPageByUuid(String uuid) {
        Page target = this.apiClient.RetrievePage(uuid);
        Entity entity = new Entity(target, apiClient.serialize(target).toString(), sessionUuid);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Page.class);
        enhancer.setInterfaces(new Class[]{EntitySession.class});
        enhancer.setCallback(entity);

        Page page = (Page) enhancer.create();
        entities.add((EntitySession) page);

        return page;
    }

    @Override
    public Database getDatabaseByUuid(String uuid) {
        Database target = this.apiClient.RetrieveDatabase(uuid);
        Entity entity = new Entity(target, apiClient.serialize(target).toString(), sessionUuid);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Database.class);
        enhancer.setInterfaces(new Class[]{EntitySession.class});
        enhancer.setCallback(entity);

        Database database = (Database) enhancer.create();
        entities.add((EntitySession) database);

        return database;
    }

    @Override
    public void flush() {
        for(EntitySession entitySession: entities){
            if(isDirty(entitySession)){
                if(Page.class.isAssignableFrom(entitySession.getClass())){
                    Page page = (Page) entitySession;
                    RequestUpdatePage requestUpdatePage = RequestUpdatePage.of(page);
                    apiClient.UpdatePage(page.getId(), requestUpdatePage);
                } else if(Database.class.isAssignableFrom(entitySession.getClass())){
                    Database database = (Database) entitySession;
                    RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(database);
                    apiClient.UpdateDatabase(database.getId(), requestUpdateDatabase);
                }
            }
        }
    }

    public boolean isDirty(Page page){
        return isDirty((Object) page);
    }

    public boolean isDirty(Database database){
        return isDirty((Object) database);
    }

    private boolean isDirty(Object o){
        if(!Enhancer.isEnhanced(o.getClass())){
            return true;
        } else {
            String serialize = apiClient.serialize(o).toString();
            EntitySession entitySession = (EntitySession) o;
            return !entitySession.getEntitySnapshot().equals(serialize);
        }
    }
}
