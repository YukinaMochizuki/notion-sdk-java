package tw.yukina.notion.sdk.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.entity.EntityInterceptor;
import tw.yukina.notion.sdk.client.entity.Entity;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;

@Getter
@Setter
public class NotionSessionImpl implements NotionSession {

    private final ApiClient apiClient;

    private final String sessionUuid = UUID.randomUUID().toString();

    private List<Entity> entities = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    private boolean isClosed = false;

    public NotionSessionImpl(ApiClient apiClient){
        this.apiClient = apiClient;
    }

    @Override
    public Page getPageByUuid(String uuid) {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(Page.class.isAssignableFrom(entity.getClass())){
                Page page = (Page) entity;
                if(page.getId().equals(uuid)) return page;
            }
        }

        Page target = this.apiClient.RetrievePage(uuid);
        NotionObject notionObject = NotionObject.of(target);
        Entity entity = new EntityInterceptor(apiClient.serialize(target).toString(), sessionUuid);
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> implementationDefinition =
                new ByteBuddy()
                        .subclass(Page.class)
                        .implement(Entity.class)
                        .method(isDeclaredBy(Page.class))
                        .intercept(MethodDelegation.withDefaultConfiguration().to(target));

        Class<?> pageClass = wrapNotionObjectEntity(implementationDefinition, notionObject, entity);

        Page page;

        try {
            page = (Page) pageClass.newInstance();
            entities.add((Entity) page);
            return page;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Database getDatabaseByUuid(String uuid) {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(Database.class.isAssignableFrom(entity.getClass())){
                Database database = (Database) entity;
                if(database.getId().equals(uuid)) return database;
            }
        }

        Database target = this.apiClient.RetrieveDatabase(uuid);
        NotionObject notionObject = NotionObject.of(target);
        Entity entity = new EntityInterceptor(apiClient.serialize(target).toString(), sessionUuid);
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> implementationDefinition =
                new ByteBuddy()
                        .subclass(Database.class)
                        .implement(Entity.class)
                        .method(isDeclaredBy(Database.class))
                        .intercept(MethodDelegation.withDefaultConfiguration().to(target));

        Class<?> databaseClass = wrapNotionObjectEntity(implementationDefinition, notionObject, entity);

        Database database;

        try {
            database = (Database) databaseClass.newInstance();
            entities.add((Entity) database);
            return database;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void flush() {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(isDirty(entity)){
                if(Page.class.isAssignableFrom(entity.getClass())){
                    Page page = (Page) entity;
                    RequestUpdatePage requestUpdatePage = RequestUpdatePage.of(page);
                    apiClient.UpdatePage(page.getId(), requestUpdatePage);
                } else if(Database.class.isAssignableFrom(entity.getClass())){
                    Database database = (Database) entity;
                    RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(database);
                    apiClient.UpdateDatabase(database.getId(), requestUpdateDatabase);
                }
            }
            entity.setEntitySnapshot(apiClient.serialize(entity).toString());
        }
    }

    @Override
    public void close() {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        this.entities = null;
        this.isClosed = true;
    }

    public boolean isDirty(Page page){
        return isDirty((Object) page);
    }

    public boolean isDirty(Database database){
        return isDirty((Object) database);
    }

    private boolean isDirty(Object o){
        if(isClosed) throw new NotionSessionException("Session was already closed");

        if(Arrays.stream(o.getClass().getInterfaces()).noneMatch(clazz -> clazz.equals(Entity.class))){
            return true;
        } else {
            String serialize = apiClient.serialize(o).toString();
            Entity entity = (Entity) o;
            return !entity.getEntitySnapshot().equals(serialize);
        }
    }

    private Class<?> wrapNotionObjectEntity(@NotNull DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> implementationDefinition,
                                            NotionObject notionObject, Entity entity){
        return implementationDefinition.method(isDeclaredBy(NotionObject.class))
                .intercept(MethodDelegation.withDefaultConfiguration()
                        .to(notionObject))
                .method(isDeclaredBy(Entity.class))
                .intercept(MethodDelegation.withDefaultConfiguration().to(entity))
                .make()
                .load(getClass().getClassLoader()).getLoaded();
    }
}
