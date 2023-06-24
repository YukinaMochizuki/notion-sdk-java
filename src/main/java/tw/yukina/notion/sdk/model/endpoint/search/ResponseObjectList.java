package tw.yukina.notion.sdk.model.endpoint.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.endpoint.ResponseList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseObjectList extends ResponseList {

    private static final String RESULTS_FIELD = "results";

    @JsonProperty(RESULTS_FIELD)
    private List<NotionObject> blocks = new ArrayList<>();
}
