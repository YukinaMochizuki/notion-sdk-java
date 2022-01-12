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
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
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

        Page target = this.apiClient.retrievePage(uuid);
        return getPageEntity(target);
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

        Database target = this.apiClient.retrieveDatabase(uuid);
        return getDatabaseEntity(target);
    }

    @Override
    public Page save(@NotNull Page page) {
        if(Arrays.asList(page.getClass().getInterfaces()).contains(Entity.class)){
            Entity entity = (Entity) page;
            if(!entity.getSessionUuid().equals(this.sessionUuid)) throw new NotionSessionException("The target session is incorrect");
            else if(isDirty(page)){
                RequestUpdatePage requestUpdatePage = RequestUpdatePage.of(page);
                apiClient.updatePage(page.getId(), requestUpdatePage);
                entity.setEntitySnapshot(apiClient.serialize(entity).toString());
                return page;
            } else return page;
        } else {
            RequestCreatePage requestCreatePage = new RequestCreatePage();
            requestCreatePage.setParent(page.getParent());
            requestCreatePage.setProperties(page.getPropertyMap());
            requestCreatePage.setFileObject(page.getCover());
            requestCreatePage.setIcon(page.getIcon());

            Page target = this.apiClient.createPage(requestCreatePage);
            return getPageEntity(target);
        }
    }

    @Override
    public Database save(@NotNull Database database) {
        if(Arrays.asList(database.getClass().getInterfaces()).contains(Entity.class)){
            Entity entity = (Entity) database;
            if(!entity.getSessionUuid().equals(this.sessionUuid)) throw new NotionSessionException("The target session is incorrect");
            else if(isDirty(database)){
                RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(database);
                apiClient.updateDatabase(database.getId(), requestUpdateDatabase);
                entity.setEntitySnapshot(apiClient.serialize(entity).toString());
                return database;
            } else return database;
        } else {
            RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
            requestCreateDatabase.setTitle(database.getTitle());
            requestCreateDatabase.setProperties(database.getPropertyMap());
            requestCreateDatabase.setParent(database.getParent());

            Database target = this.apiClient.createDatabase(requestCreateDatabase);
            return getDatabaseEntity(target);
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
                    apiClient.updatePage(page.getId(), requestUpdatePage);
                } else if(Database.class.isAssignableFrom(entity.getClass())){
                    Database database = (Database) entity;
                    RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(database);
                    apiClient.updateDatabase(database.getId(), requestUpdateDatabase);
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

    @NotNull
    private Page getPageEntity(Page target){
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

    @NotNull
    private Database getDatabaseEntity(Database target){
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
