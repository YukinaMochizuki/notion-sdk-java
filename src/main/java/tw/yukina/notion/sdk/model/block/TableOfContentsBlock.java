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
public class TableOfContentsBlock extends Block {

    private static final String TABLE_OF_CONTENTS_FIELD = "table_of_contents";

    @JsonProperty(TABLE_OF_CONTENTS_FIELD)
    private TableOfContents tableOfContents;

    @NotNull
    public static TableOfContentsBlock of(){
        TableOfContentsBlock tableOfContentsBlock = new TableOfContentsBlock();
        tableOfContentsBlock.setType(BlockType.TABLE_OF_CONTENTS);
        tableOfContentsBlock.setTableOfContents(new TableOfContents(TextColor.DEFAULT));
        return tableOfContentsBlock;
    }

    @NotNull
    public static TableOfContentsBlock of(@NotNull TextColor textColor){
        TableOfContentsBlock tableOfContentsBlock = new TableOfContentsBlock();
        tableOfContentsBlock.setType(BlockType.TABLE_OF_CONTENTS);
        tableOfContentsBlock.setTableOfContents(new TableOfContents(textColor));
        return tableOfContentsBlock;
    }
}