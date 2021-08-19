package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.parent.Parent;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Database {
    private static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String CREATED_TIME_FIELD = "created_time";
    private static final String LAST_EDITED_TIME_FIELD = "last_edited_time";
    private static final String TITLE_FIELD = "title";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String PARENT_FIELD = "parent";

    @JsonIgnore
    private ObjectType objectType = ObjectType.DATABASE;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(CREATED_TIME_FIELD)
    private ZonedDateTime createdTime;

    @JsonProperty(LAST_EDITED_TIME_FIELD)
    private ZonedDateTime lastEditedTime;

    @JsonProperty(TITLE_FIELD)
    private List<RichText> title;

    @JsonProperty(PARENT_FIELD)
    private Parent parent;
}
