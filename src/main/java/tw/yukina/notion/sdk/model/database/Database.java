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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = JsonDeserializer.None.class)
public class Database extends NotionObject {
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
        Database database = (Database) o;
        return getObjectType() == database.getObjectType() &&
                Objects.equals(getId(), database.getId()) &&
                Objects.equals(getCreatedTime(), database.getCreatedTime()) &&
                Objects.equals(getLastEditedTime(), database.getLastEditedTime()) &&
                Objects.equals(title, database.title) &&
                Objects.equals(description, database.description) &&
                Objects.equals(icon, database.icon) &&
                Objects.equals(cover, database.cover) &&
                propertyMap.equals(database.propertyMap) &&
                Objects.equals(parent, database.parent) &&
                Objects.equals(url, database.url) &&
                Objects.equals(archived, database.archived) &&
                Objects.equals(isInline, database.isInline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getObjectType(), getId(), getCreatedTime(), getLastEditedTime(), title, description, icon,
                cover, propertyMap, parent, url, archived, isInline);
    }
}
