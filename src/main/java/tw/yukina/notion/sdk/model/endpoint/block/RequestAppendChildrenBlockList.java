package tw.yukina.notion.sdk.model.endpoint.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.block.BlockModel;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestAppendChildrenBlockList {

    private static final String CHILDREN_FIELD = "children";

    @JsonProperty(CHILDREN_FIELD)
    private List<? extends BlockModel> blocks = new ArrayList<>();

}
