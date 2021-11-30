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
public class TableOfContentsBlock extends Block {

    private static final String TABLE_OF_CONTENTS_FIELD = "table_of_contents";

    @JsonProperty(TABLE_OF_CONTENTS_FIELD)
    private EmptyObject emptyObject;

    @NotNull
    public static TableOfContentsBlock of(){
        TableOfContentsBlock tableOfContentsBlock = new TableOfContentsBlock(EmptyObject.of());
        tableOfContentsBlock.setType(BlockType.TABLE_OF_CONTENTS);
        return tableOfContentsBlock;
    }
}