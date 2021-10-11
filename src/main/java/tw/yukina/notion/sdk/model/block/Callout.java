package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Callout {

    private static final String TEXT_FIELD = "text";
    private static final String ICON_FIELD = "icon";
    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> richTexts;

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @Nullable
    @JsonProperty(CHILDREN_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;
}
