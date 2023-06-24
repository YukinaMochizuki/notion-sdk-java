package tw.yukina.notion.sdk.model.common.parent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class BlockParent extends Parent {

    private static final String BLOCK_ID_FIELD = "block_id";

    @JsonProperty(BLOCK_ID_FIELD)
    private String blockId;

    @NotNull
    public static BlockParent of(String blockId) {
        BlockParent pageParent = new BlockParent();
        pageParent.setParentType(ParentType.PAGE);
        pageParent.setBlockId(blockId);
        return pageParent;
    }
}
