package tw.yukina.notion.sdk.model.common.unit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BlockUnit {

    private static final String BLOCK_ID_FIELD = "block_id";
    private static final String TYPE_FIELD = "type";

    @JsonProperty(BLOCK_ID_FIELD)
    private String blockId;

    @JsonProperty(TYPE_FIELD)
    private String type = "block_id";

    @NotNull
    public static BlockUnit of(String id){
        BlockUnit blockUnit = new BlockUnit();
        blockUnit.setBlockId(id);
        return blockUnit;
    }
}
