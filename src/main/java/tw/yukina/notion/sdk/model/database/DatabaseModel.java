package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.parent.Parent;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DatabaseModel extends NotionObject {
    private static final String TITLE_FIELD = "title";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String PARENT_FIELD = "parent";
    private static final String URL_FIELD = "url";
    private static final String ARCHIVED_FIELD = "archived";
    private static final String IS_INLINE_FIELD = "is_inline";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.DATABASE;

    @JsonProperty(TITLE_FIELD)
    private List<RichText> title;

    @JsonProperty(DESCRIPTION_FIELD)
    private List<RichText> description;

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @JsonProperty(COVER_FIELD)
    private FileObject cover;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, DatabaseProperty> propertyMap;

    @JsonProperty(PARENT_FIELD)
    private Parent parent;

    @JsonProperty(URL_FIELD)
    private String url;

    @JsonProperty(ARCHIVED_FIELD)
    private boolean archived;

    @JsonProperty(IS_INLINE_FIELD)
    private boolean isInline;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseModel databaseModel = (DatabaseModel) o;
        return getObjectType() == databaseModel.getObjectType() &&
                Objects.equals(getId(), databaseModel.getId()) &&
                Objects.equals(getCreatedTime(), databaseModel.getCreatedTime()) &&
                Objects.equals(getLastEditedTime(), databaseModel.getLastEditedTime()) &&
                Objects.equals(title, databaseModel.title) &&
                Objects.equals(description, databaseModel.description) &&
                Objects.equals(icon, databaseModel.icon) &&
                Objects.equals(cover, databaseModel.cover) &&
                propertyMap.equals(databaseModel.propertyMap) &&
                Objects.equals(parent, databaseModel.parent) &&
                Objects.equals(url, databaseModel.url) &&
                Objects.equals(archived, databaseModel.archived) &&
                Objects.equals(isInline, databaseModel.isInline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectType(), getId(), getCreatedTime(), getLastEditedTime(), title, description, icon,
                cover, propertyMap, parent, url, archived, isInline);
    }
}
