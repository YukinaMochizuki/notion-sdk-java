package tw.yukina.notion.sdk.model.endpoint;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestChildrenBlockList {

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(CHILDREN_FIELD)
    private List<Block> blocks = new ArrayList<>();

}
