package tw.yukina.notion.sdk.model.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.Parent;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.page.property.PageProperty;
import tw.yukina.notion.sdk.model.page.property.TitleProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PageModel extends NotionObject {

    private static final String ARCHIVED_FIELD = "archived";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String PARENT_FIELD = "parent";
    private static final String URL_FIELD = "url";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.PAGE;

    @JsonProperty(COVER_FIELD)
    private FileObject cover;

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @JsonProperty(PARENT_FIELD)
    private Parent parent;

    @JsonProperty(ARCHIVED_FIELD)
    private boolean archived;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, PageProperty> propertyMap = new HashMap<>();

    @JsonProperty(URL_FIELD)
    private String url;

    @JsonIgnore
    public Optional<String> getTitle(){
        TitleProperty titleProperty = (TitleProperty) propertyMap.get("Name");

        if(titleProperty == null)return Optional.empty();
        else return Optional.of(RichTextHelper.textsToString(titleProperty.getTexts()));
    }

    @NotNull
    public static PageModel ofDatabasePage(String databaseId){
        DatabaseParent databaseParent = DatabaseParent.of(databaseId);
        PageModel pageModel = new PageModel();
        pageModel.setParent(databaseParent);
        return pageModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageModel pageModel = (PageModel) o;
        return archived == pageModel.archived &&
                getObjectType() == pageModel.getObjectType() &&
                Objects.equals(getId(), pageModel.getId()) &&
                Objects.equals(getCreatedTime(), pageModel.getCreatedTime()) &&
                Objects.equals(getLastEditedTime(), pageModel.getLastEditedTime()) &&
                Objects.equals(icon, pageModel.icon) &&
                Objects.equals(cover, pageModel.cover) &&
                propertyMap.equals(pageModel.propertyMap) &&
                Objects.equals(parent, pageModel.parent) &&
                Objects.equals(url, pageModel.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectType(), getId(), getCreatedTime(), getLastEditedTime(), archived, icon, cover, propertyMap, parent, url);
    }
}
