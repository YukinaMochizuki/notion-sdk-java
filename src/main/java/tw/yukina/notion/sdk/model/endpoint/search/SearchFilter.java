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
public class SearchFilter {

    private static final String VALUE_FIELD = "value";
    private static final String PROPERTY_FIELD = "property";

    @JsonProperty(VALUE_FIELD)
    private FilterValueEnum filterValue;

    @JsonProperty(PROPERTY_FIELD)
    private String filterProperty = "object";

    @NotNull
    public static SearchFilter of(FilterValueEnum filterValue) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setFilterValue(filterValue);
        return searchFilter;
    }
}
