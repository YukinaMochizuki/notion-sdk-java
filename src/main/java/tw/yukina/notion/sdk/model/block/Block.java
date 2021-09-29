package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.deserializer.BlockDeserializer;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonDeserialize(using = BlockDeserializer.class)
public class Block {

    private static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String TYPE_FIELD = "type";
    private static final String CREATED_TIME_FIELD = "created_time";
    private static final String LAST_EDITED_TIME_FIELD = "last_edited_time";
    private static final String HAS_CHILDREN_FIELD = "has_children";
    private static final String ARCHIVED_FIELD = "archived";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.BLOCK;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(TYPE_FIELD)
    private BlockType type;

    @JsonProperty(CREATED_TIME_FIELD)
    private ZonedDateTime createdTime;

    @JsonProperty(LAST_EDITED_TIME_FIELD)
    private ZonedDateTime lastEditedTime;

    @JsonProperty(HAS_CHILDREN_FIELD)
    private Boolean hasChildren = false;

    @JsonProperty(ARCHIVED_FIELD)
    private Boolean archived = false;
}
