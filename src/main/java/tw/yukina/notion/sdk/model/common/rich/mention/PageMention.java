package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tw.yukina.notion.sdk.model.common.unit.Page;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageMention extends Mention {
    private static final String PAGE_FIELD = "page";

    private Page page;
}
