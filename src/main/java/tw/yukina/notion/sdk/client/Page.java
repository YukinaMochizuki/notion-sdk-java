package tw.yukina.notion.sdk.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import tw.yukina.notion.sdk.model.page.PageModel;

public class Page extends PageModel implements Entity<Page> {

    private final NotionClient notionClient;

    @Getter
    @JsonIgnore
    protected String entitySnapshot;

    public Page(NotionClient notionClient, PageModel pageModel) {
        this.notionClient = notionClient;
        this.setId(pageModel.getId());
        this.setArchived(pageModel.isArchived());
        this.setCreatedTime(pageModel.getCreatedTime());
        this.setLastEditedTime(pageModel.getLastEditedTime());
        this.setParent(pageModel.getParent());
        this.setPropertyMap(pageModel.getPropertyMap());
        this.setUrl(pageModel.getUrl());

        entitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this));
    }

    @Override
    public Page refetch() {
        return notionClient.getPageByUuid(this.getId());
    }

    @Override
    public Page save() {
        return notionClient.save(this);
    }

    @Override
    public void remove() {
        notionClient.remove(this);
    }

    @Override
    @JsonIgnore
    public Boolean isDirty() {
        String newEntitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this));
        return !newEntitySnapshot.equals(entitySnapshot);
    }
}
