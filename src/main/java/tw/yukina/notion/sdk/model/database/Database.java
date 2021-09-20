package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
public class Database {
    private static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String CREATED_TIME_FIELD = "created_time";
    private static final String LAST_EDITED_TIME_FIELD = "last_edited_time";
    private static final String TITLE_FIELD = "title";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String PARENT_FIELD = "parent";
    private static final String URL_FIELD = "url";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.DATABASE;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(CREATED_TIME_FIELD)
    private ZonedDateTime createdTime;

    @JsonProperty(LAST_EDITED_TIME_FIELD)
    private ZonedDateTime lastEditedTime;

    @JsonProperty(TITLE_FIELD)
    private List<RichText> title;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Database database = (Database) o;
        return objectType == database.objectType &&
                Objects.equals(id, database.id) &&
                Objects.equals(createdTime, database.createdTime) &&
                Objects.equals(lastEditedTime, database.lastEditedTime) &&
                Objects.equals(title, database.title) &&
                Objects.equals(icon, database.icon) &&
                Objects.equals(cover, database.cover) &&
                propertyMap.equals(database.propertyMap) &&
                Objects.equals(parent, database.parent) &&
                Objects.equals(url, database.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectType, id, createdTime, lastEditedTime, title, icon, cover, propertyMap, parent, url);
    }
}
