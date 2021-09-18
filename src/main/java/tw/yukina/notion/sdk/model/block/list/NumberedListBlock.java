package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class NumberedListBlock extends Block implements ListBlock {
    private static final String NUMBERED_LIST_FIELD = "numbered_list_item";

    @JsonProperty(NUMBERED_LIST_FIELD)
    private ListObject listObject;
}
