package tw.yukina.notion.sdk.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.page.PageModel;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

public class Page extends PageModel implements Entity<Page> {

    private final NotionClient notionClient;

    @Getter
    @JsonIgnore
    protected String entitySnapshot;

    public Page(NotionClient notionClient, PageModel pageModel) {
        this.notionClient = notionClient;
        this.setObjectType(pageModel.getObjectType());
        this.setId(pageModel.getId());
        this.setCreatedTime(pageModel.getCreatedTime());
        this.setLastEditedTime(pageModel.getLastEditedTime());
        this.setCreatedBy(pageModel.getCreatedBy());
        this.setLastEditedBy(pageModel.getLastEditedBy());
        this.setCover(pageModel.getCover());
        this.setIcon(pageModel.getIcon());
        this.setParent(pageModel.getParent());
        this.setArchived(pageModel.isArchived());
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
        if (isDirty()) {
            return notionClient.save(this);
        } else return this;
    }

    @Override
    public void remove() {
        notionClient.remove(this);
    }

    public Page restore() {
        return notionClient.restorePage(this.getId());
    }

    @Override
    @JsonIgnore
    public Boolean isDirty() {
        if (this.getId() == null) return true;
        String newEntitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this));
        return !newEntitySnapshot.equals(entitySnapshot);
    }

    public Page putProperty(String name, PageProperty property) {
        this.getPropertyMap().put(name, property);
        return this;
    }

    public PageProperty getProperty(String name) {
        return this.getPropertyMap().get(name);
    }

    public Page setTitle(String title) {
        this.getPropertyMap().get("Name").asTitleProperty().setTexts(RichTextHelper.createDefaultArrayText(title));
        return this;
    }
}
