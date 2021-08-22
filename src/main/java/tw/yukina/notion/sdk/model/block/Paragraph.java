package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Paragraph {
    private static final String TEXT_FIELD = "text";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> richTexts;

    @Nullable
    @JsonProperty(CHILDREN_FIELD)
    private List<Block> children;
}
