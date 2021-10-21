package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.deserializer.BlockDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = BlockDeserializer.class)
public class Block extends NotionObject {

    private static final String TYPE_FIELD = "type";
    private static final String HAS_CHILDREN_FIELD = "has_children";
    private static final String ARCHIVED_FIELD = "archived";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType = ObjectType.BLOCK;

    @JsonProperty(TYPE_FIELD)
    private BlockType type;

    @JsonProperty(HAS_CHILDREN_FIELD)
    private Boolean hasChildren = false;

    @JsonProperty(ARCHIVED_FIELD)
    private Boolean archived = false;
}
