package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.endpoint.ResponseList;
import tw.yukina.notion.sdk.model.page.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponsePageList  extends ResponseList {
    private static final String RESULTS_FIELD = "results";

    @JsonProperty(RESULTS_FIELD)
    private List<Page> pages;
}
