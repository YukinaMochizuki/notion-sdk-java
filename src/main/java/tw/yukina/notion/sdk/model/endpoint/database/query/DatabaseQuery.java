package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseQuery {

    public static final String FILTER_FIELD = "filter";
    public static final String SORTS_FIELD = "sorts";
    public static final String START_CURSOR_FIELD = "start_cursor";
    public static final String PAGE_SIZE_FIELD = "page_size";

    @JsonProperty(FILTER_FIELD)
    private Compound compound;

    @JsonProperty(SORTS_FIELD)
    private List<DatabaseSort> sorts = new ArrayList<>();

    @JsonProperty(START_CURSOR_FIELD)
    private String startCursor;

    @JsonProperty(PAGE_SIZE_FIELD)
    private Integer pageSize;

}
