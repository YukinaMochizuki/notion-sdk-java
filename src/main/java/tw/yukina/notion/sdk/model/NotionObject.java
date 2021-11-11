package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.deserializer.NotionObjectDeserializer;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = NotionObjectDeserializer.class)
public class NotionObject {

    public static final String OBJECT_FIELD = "object";
    private static final String ID_FIELD = "id";
    private static final String CREATED_TIME_FIELD = "created_time";
    private static final String LAST_EDITED_TIME_FIELD = "last_edited_time";

    @JsonProperty(OBJECT_FIELD)
    private ObjectType objectType;

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(CREATED_TIME_FIELD)
    private ZonedDateTime createdTime;

    @JsonProperty(LAST_EDITED_TIME_FIELD)
    private ZonedDateTime lastEditedTime;

    @NotNull
    public static NotionObject of(@NotNull NotionObject target){
        NotionObject notionObject = new NotionObject();
        notionObject.setId(target.getId());
        notionObject.setCreatedTime(target.getCreatedTime());
        notionObject.setLastEditedTime(target.getLastEditedTime());
        notionObject.setObjectType(target.getObjectType());
        return notionObject;
    }

}
