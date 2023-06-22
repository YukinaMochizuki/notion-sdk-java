package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class BookmarkBlockModel extends BlockModel {
    private static final String BOOKMARK_FIELD = "bookmark";

    @JsonProperty(BOOKMARK_FIELD)
    private Bookmark bookmark;

    @NotNull
    public static BookmarkBlockModel of(String url){
        Bookmark bookmark = new Bookmark();
        bookmark.setUrl(url);
        BookmarkBlockModel bookmarkBlock = new BookmarkBlockModel();
        bookmarkBlock.setBookmark(bookmark);
        bookmarkBlock.setType(BlockType.BOOKMARK);
        return bookmarkBlock;
    }

    @NotNull
    public static BookmarkBlockModel of(String url, String caption){
        List<RichText> richTexts = TextBuilder.of(caption).build();
        return of(url, richTexts);
    }

    @NotNull
    public static BookmarkBlockModel of(String url, List<RichText> caption){
        Bookmark bookmark = new Bookmark();
        bookmark.setUrl(url);
        bookmark.setRichTexts(caption);
        BookmarkBlockModel bookmarkBlock = new BookmarkBlockModel();
        bookmarkBlock.setBookmark(bookmark);
        bookmarkBlock.setType(BlockType.BOOKMARK);
        return bookmarkBlock;
    }

    @Override
    public boolean canHaveChildren() {
        return false;
    }
}
