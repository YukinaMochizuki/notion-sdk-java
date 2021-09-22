package tw.yukina.notion.sdk.model.endpoint.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.endpoint.ResponseList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseBlockList extends ResponseList {

    private static final String RESULTS_FIELD = "results";

    @JsonProperty(RESULTS_FIELD)
    private List<Block> blocks = new ArrayList<>();

}
