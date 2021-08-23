package tw.yukina.notion.sdk.model.block.list;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListObject {
    private static final String TEXT_FIELD = "text";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> texts;

    @Nullable
    @JsonProperty(CHILDREN_FIELD)
    private List<Block> children;
}
