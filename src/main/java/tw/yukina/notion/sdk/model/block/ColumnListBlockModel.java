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
public class ColumnListBlockModel extends BlockModel {

    private static final String COLUMN_LIST_FIELD = "column_list";

    @JsonProperty(COLUMN_LIST_FIELD)
    private EmptyObject emptyObject;

    @Override
    public boolean canHaveChildren() {
        return false;
    }

    @NotNull
    public static ColumnListBlockModel of() {
        ColumnListBlockModel columnListBlock = new ColumnListBlockModel(EmptyObject.of());
        columnListBlock.setType(BlockType.COLUMN_LIST);
        return columnListBlock;
    }
}