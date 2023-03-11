package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Paragraph {
    private static final String RICH_TEXT_FIELD = "rich_text";

    private static final String COLOR_FIELD = "color";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(RICH_TEXT_FIELD)
    private List<RichText> richTexts;

    @JsonProperty(COLOR_FIELD)
    private TextColor color;

    @Nullable
    @JsonProperty(CHILDREN_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;
}
