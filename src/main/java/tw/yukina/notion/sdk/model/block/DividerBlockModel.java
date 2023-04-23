package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.EmptyObject;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DividerBlockModel extends BlockModel {

    private static final String DIVIDER_FIELD = "divider";

    @JsonProperty(DIVIDER_FIELD)
    private EmptyObject emptyObject;

    @NotNull
    public static DividerBlockModel of(){
        DividerBlockModel dividerBlock = new DividerBlockModel(EmptyObject.of());
        dividerBlock.setType(BlockType.DIVIDER);
        return dividerBlock;
    }
}
