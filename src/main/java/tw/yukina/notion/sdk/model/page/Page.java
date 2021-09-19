package tw.yukina.notion.sdk.model.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.parent.Parent;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Page {

    private static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String CREATED_TIME_FIELD = "created_time";
    private static final String LAST_EDITED_TIME_FIELD = "last_edited_time";
    private static final String ARCHIVED_FIELD = "archived";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String PARENT_FIELD = "parent";
    private static final String URL_FIELD = "url";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.PAGE;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(CREATED_TIME_FIELD)
    private ZonedDateTime createdTime;

    @JsonProperty(LAST_EDITED_TIME_FIELD)
    private ZonedDateTime lastEditedTime;

    @JsonProperty(COVER_FIELD)
    private FileObject cover;

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @JsonProperty(PARENT_FIELD)
    private Parent parent;

    @JsonProperty(ARCHIVED_FIELD)
    private boolean archived;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, PageProperty> propertyMap;

    @JsonProperty(URL_FIELD)
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return archived == page.archived &&
                objectType == page.objectType &&
                Objects.equals(id, page.id) &&
                Objects.equals(createdTime, page.createdTime) &&
                Objects.equals(lastEditedTime, page.lastEditedTime) &&
                Objects.equals(icon, page.icon) &&
                Objects.equals(cover, page.cover) &&
                propertyMap.equals(page.propertyMap) &&
                Objects.equals(parent, page.parent) &&
                Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectType, id, createdTime, lastEditedTime, archived, icon, cover, propertyMap, parent, url);
    }
}
