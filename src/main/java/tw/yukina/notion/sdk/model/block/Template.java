package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Template {

    private static final String RICH_TEXT_FIELD = "rich_text";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(RICH_TEXT_FIELD)
    private List<RichText> title;

    @JsonProperty(CHILDREN_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;

}
