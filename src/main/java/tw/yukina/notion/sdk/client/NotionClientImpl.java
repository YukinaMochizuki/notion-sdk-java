package tw.yukina.notion.sdk.client;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientFactory;
import tw.yukina.notion.sdk.client.entity.EntityInterceptor;
import tw.yukina.notion.sdk.client.entity.EntitySession;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;

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
        NotionObject notionObject = NotionObject.of(target);
        EntitySession entitySession = new EntityInterceptor(apiClient.serialize(target).toString(), sessionUuid);

        Class<?> pageClass = new ByteBuddy()
                .subclass(Page.class)
                .implement(EntitySession.class)
                .method(isDeclaredBy(Page.class))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .to(target))
                .method(isDeclaredBy(NotionObject.class))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .to(notionObject))
                .method(isDeclaredBy(EntitySession.class))
                .intercept(MethodDelegation.withDefaultConfiguration().to(entitySession))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        Page page;

        try {
            page = (Page) pageClass.newInstance();
            entities.add((EntitySession) page);
            return page;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Database getDatabaseByUuid(String uuid) {
        return null;
    }

    @Override
    public void flush() {
        for(EntitySession entitySession: entities){
            System.out.println("In flush " + Page.class.isAssignableFrom(entitySession.getClass()));
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
        if(Arrays.stream(o.getClass().getInterfaces()).noneMatch(clazz -> clazz.equals(EntitySession.class))){
            return true;
        } else {
            String serialize = apiClient.serialize(o).toString();
            EntitySession entitySession = (EntitySession) o;
            return !entitySession.getEntitySnapshot().equals(serialize);
        }
    }
}
