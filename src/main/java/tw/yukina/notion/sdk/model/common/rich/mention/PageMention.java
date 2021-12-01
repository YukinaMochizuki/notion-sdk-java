package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.unit.PageUnit;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PageMention extends Mention {
    private static final String PAGE_FIELD = "page";

    private PageUnit page;

    @NotNull
    public static PageMention of(String pageId){
        PageUnit page = new PageUnit();
        page.setPageId(pageId);
        PageMention pageMention = new PageMention();
        pageMention.setMentionType(MentionType.PAGE);
        pageMention.setPage(page);
        return pageMention;
    }
}
