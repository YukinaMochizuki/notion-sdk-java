package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.TextBlock;
import tw.yukina.notion.sdk.model.block.Paragraph;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class BulletedListBlockModel extends BlockModel implements TextBlock {
    private static final String BULLETED_LIST_FIELD = "bulleted_list_item";

    @JsonProperty(BULLETED_LIST_FIELD)
    private Paragraph paragraph;

}
