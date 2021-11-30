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
public class ColumnBlock extends Block {

    private static final String COLUMN_FIELD = "column";

    @JsonProperty(COLUMN_FIELD)
    private EmptyObject emptyObject;

    @NotNull
    public static ColumnBlock of(){
        ColumnBlock columnBlock = new ColumnBlock(EmptyObject.of());
        columnBlock.setType(BlockType.COLUMN);
        return columnBlock;
    }
}