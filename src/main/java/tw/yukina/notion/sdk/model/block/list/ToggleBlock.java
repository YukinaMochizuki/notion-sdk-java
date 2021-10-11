package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.TextBlock;
import tw.yukina.notion.sdk.model.block.Paragraph;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ToggleBlock extends Block implements TextBlock {
    private static final String TOGGLE_FIELD = "toggle";

    @JsonProperty(TOGGLE_FIELD)
    private Paragraph paragraph;
}
