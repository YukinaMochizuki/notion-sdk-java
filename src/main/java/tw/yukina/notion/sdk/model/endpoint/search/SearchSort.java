package tw.yukina.notion.sdk.model.endpoint.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SearchSort {

    private static final String DIRECTION_FIELD = "direction";
    private static final String TIMESTAMP_FIELD = "timestamp";

    @JsonProperty(DIRECTION_FIELD)
    private DirectionEnum direction;

    @JsonProperty(TIMESTAMP_FIELD)
    private String timestamp = "last_edited_time";

    @NotNull
    public static SearchSort of(DirectionEnum direction){
        SearchSort searchSort = new SearchSort();
        searchSort.setDirection(direction);
        return searchSort;
    }
}
