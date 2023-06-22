package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.TextColor;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class TableOfContentsBlockModel extends BlockModel {

    private static final String TABLE_OF_CONTENTS_FIELD = "table_of_contents";

    @JsonProperty(TABLE_OF_CONTENTS_FIELD)
    private TableOfContents tableOfContents;

    @Override
    public boolean canHaveChildren() {
        return false;
    }

    @NotNull
    public static TableOfContentsBlockModel of() {
        TableOfContentsBlockModel tableOfContentsBlock = new TableOfContentsBlockModel();
        tableOfContentsBlock.setType(BlockType.TABLE_OF_CONTENTS);
        tableOfContentsBlock.setTableOfContents(new TableOfContents(TextColor.DEFAULT));
        return tableOfContentsBlock;
    }

    @NotNull
    public static TableOfContentsBlockModel of(@NotNull TextColor textColor) {
        TableOfContentsBlockModel tableOfContentsBlock = new TableOfContentsBlockModel();
        tableOfContentsBlock.setType(BlockType.TABLE_OF_CONTENTS);
        tableOfContentsBlock.setTableOfContents(new TableOfContents(textColor));
        return tableOfContentsBlock;
    }
}