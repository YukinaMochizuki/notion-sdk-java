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
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.PageModel;

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
    public PageModel getPageByUuid(String uuid) {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(PageModel.class.isAssignableFrom(entity.getClass())){
                PageModel pageModel = (PageModel) entity;
                if(pageModel.getId().equals(uuid)) return pageModel;
            }
        }

        PageModel target = this.apiClient.retrievePage(uuid);
        return getPageEntity(target);
    }

    @Override
    public DatabaseModel getDatabaseByUuid(String uuid) {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(DatabaseModel.class.isAssignableFrom(entity.getClass())){
                DatabaseModel databaseModel = (DatabaseModel) entity;
                if(databaseModel.getId().equals(uuid)) return databaseModel;
            }
        }

        DatabaseModel target = this.apiClient.retrieveDatabase(uuid);
        return getDatabaseEntity(target);
    }

    @Override
    public PageModel save(@NotNull PageModel pageModel) {
        if(Arrays.asList(pageModel.getClass().getInterfaces()).contains(Entity.class)){
            Entity entity = (Entity) pageModel;
            if(!entity.getSessionUuid().equals(this.sessionUuid)) throw new NotionSessionException("The target session is incorrect");
            else if(isDirty(pageModel)){
                RequestUpdatePage requestUpdatePage = RequestUpdatePage.of(pageModel);
                apiClient.updatePage(pageModel.getId(), requestUpdatePage);
                entity.setEntitySnapshot(apiClient.serialize(entity).toString());
                return pageModel;
            } else return pageModel;
        } else {
            RequestCreatePage requestCreatePage = new RequestCreatePage();
            requestCreatePage.setParent(pageModel.getParent());
            requestCreatePage.setProperties(pageModel.getPropertyMap());
            requestCreatePage.setFileObject(pageModel.getCover());
            requestCreatePage.setIcon(pageModel.getIcon());

            PageModel target = this.apiClient.createPage(requestCreatePage);
            return getPageEntity(target);
        }
    }

    @Override
    public DatabaseModel save(@NotNull DatabaseModel databaseModel) {
        if(Arrays.asList(databaseModel.getClass().getInterfaces()).contains(Entity.class)){
            Entity entity = (Entity) databaseModel;
            if(!entity.getSessionUuid().equals(this.sessionUuid)) throw new NotionSessionException("The target session is incorrect");
            else if(isDirty(databaseModel)){
                RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(databaseModel);
                apiClient.updateDatabase(databaseModel.getId(), requestUpdateDatabase);
                entity.setEntitySnapshot(apiClient.serialize(entity).toString());
                return databaseModel;
            } else return databaseModel;
        } else {
            RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
            requestCreateDatabase.setTitle(databaseModel.getTitle());
            requestCreateDatabase.setProperties(databaseModel.getPropertyMap());
            requestCreateDatabase.setParent(databaseModel.getParent());

            DatabaseModel target = this.apiClient.createDatabase(requestCreateDatabase);
            return getDatabaseEntity(target);
        }
    }

    @Override
    public void flush() {
        if(isClosed) throw new NotionSessionException("Session was already closed");

        for(Entity entity : entities){
            if(isDirty(entity)){
                if(PageModel.class.isAssignableFrom(entity.getClass())){
                    PageModel pageModel = (PageModel) entity;
                    RequestUpdatePage requestUpdatePage = RequestUpdatePage.of(pageModel);
                    apiClient.updatePage(pageModel.getId(), requestUpdatePage);
                } else if(DatabaseModel.class.isAssignableFrom(entity.getClass())){
                    DatabaseModel databaseModel = (DatabaseModel) entity;
                    RequestUpdateDatabase requestUpdateDatabase = RequestUpdateDatabase.of(databaseModel);
                    apiClient.updateDatabase(databaseModel.getId(), requestUpdateDatabase);
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
    private PageModel getPageEntity(PageModel target){
        NotionObject notionObject = NotionObject.of(target);
        Entity entity = new EntityInterceptor(apiClient.serialize(target).toString(), sessionUuid);
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> implementationDefinition =
                new ByteBuddy()
                        .subclass(PageModel.class)
                        .implement(Entity.class)
                        .method(isDeclaredBy(PageModel.class))
                        .intercept(MethodDelegation.withDefaultConfiguration().to(target));

        Class<?> pageClass = wrapNotionObjectEntity(implementationDefinition, notionObject, entity);

        PageModel pageModel;

        try {
            pageModel = (PageModel) pageClass.newInstance();
            entities.add((Entity) pageModel);
            return pageModel;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private DatabaseModel getDatabaseEntity(DatabaseModel target){
        NotionObject notionObject = NotionObject.of(target);
        Entity entity = new EntityInterceptor(apiClient.serialize(target).toString(), sessionUuid);
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> implementationDefinition =
                new ByteBuddy()
                        .subclass(DatabaseModel.class)
                        .implement(Entity.class)
                        .method(isDeclaredBy(DatabaseModel.class))
                        .intercept(MethodDelegation.withDefaultConfiguration().to(target));

        Class<?> databaseClass = wrapNotionObjectEntity(implementationDefinition, notionObject, entity);

        DatabaseModel databaseModel;

        try {
            databaseModel = (DatabaseModel) databaseClass.newInstance();
            entities.add((Entity) databaseModel);
            return databaseModel;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDirty(PageModel pageModel){
        return isDirty((Object) pageModel);
    }

    public boolean isDirty(DatabaseModel databaseModel){
        return isDirty((Object) databaseModel);
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
