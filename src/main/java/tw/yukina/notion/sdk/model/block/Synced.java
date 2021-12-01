package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.common.unit.BlockUnit;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Synced {

    private static final String SYNCED_FROM_FIELD = "synced_from";

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(SYNCED_FROM_FIELD)
    private BlockUnit blockUnit;

    @Nullable
    @JsonProperty(CHILDREN_FIELD)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Block> children;
}
