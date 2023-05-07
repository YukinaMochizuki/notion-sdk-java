package tw.yukina.notion.sdk.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.page.PageModel;
import tw.yukina.notion.sdk.model.page.property.PageProperty;
import tw.yukina.notion.sdk.model.page.property.TitleProperty;

import java.util.HashMap;
import java.util.Map;

public class Database extends DatabaseModel implements Entity<Database> {

    private final Notion notionClient;

    @Getter
    @JsonIgnore
    protected String entitySnapshot;

    public Database(Notion notionClient, DatabaseModel databaseModel) {
        this.notionClient = notionClient;
        this.setId(databaseModel.getId());
        this.setCreatedTime(databaseModel.getCreatedTime());
        this.setLastEditedTime(databaseModel.getLastEditedTime());
        this.setCreatedBy(databaseModel.getCreatedBy());
        this.setLastEditedBy(databaseModel.getLastEditedBy());
        this.setTitle(databaseModel.getTitle());
        this.setDescription(databaseModel.getDescription());
        this.setIcon(databaseModel.getIcon());
        this.setCover(databaseModel.getCover());
        this.setPropertyMap(databaseModel.getPropertyMap());
        this.setParent(databaseModel.getParent());
        this.setUrl(databaseModel.getUrl());
        this.setArchived(databaseModel.isArchived());
        this.setInline(databaseModel.isInline());

        entitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this));
    }

    @Override
    public Database refetch() {
        return notionClient.getDatabaseByUuid(this.getId());
    }

    @Override
    public Database save() {
        if (isDirty()) {
            return notionClient.save(this);
        } else return this;
    }

    @Override
    public void remove() {
        notionClient.remove(this);
    }

    @Override
    public Database restore() {
        return notionClient.restoreDatabase(this.getId());
    }

    @Override
    @JsonIgnore
    public Boolean isDirty() {
        String newEntitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this));
        return !newEntitySnapshot.equals(entitySnapshot);
    }

    public Page getEmptyPage() {
        return getEmptyPage("");
    }

    public Page getEmptyPage(String title) {
        return notionClient.getDatabaseEmptyPage(title, this.getId());
    }
}
