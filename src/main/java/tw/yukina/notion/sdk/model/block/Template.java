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

    private static final String TEXT_FIELD = "text";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> title;

    @JsonProperty(CHILDREN_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;

}
