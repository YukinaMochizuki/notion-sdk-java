package tw.yukina.notion.sdk.model.endpoint.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.common.query.DirectionEnum;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestSearch {

    private static final String QUERY_FIELD = "query";
    private static final String SORT_FIELD = "sort";
    private static final String FILTER_FIELD = "filter";
    private static final String START_CURSOR_FIELD = "start_cursor";
    private static final String PAGE_SIZE_FIELD = "page_size";

    @Nullable
    @JsonProperty(QUERY_FIELD)
    private String query;

    @Nullable
    @JsonProperty(SORT_FIELD)
    private SearchSort sort;

    @Nullable
    @JsonProperty(FILTER_FIELD)
    private SearchFilter searchFilter;

    @Nullable
    @JsonProperty(START_CURSOR_FIELD)
    private String startCursor;

    @JsonProperty(PAGE_SIZE_FIELD)
    private int pageSize = 100;

    @NotNull
    @Contract(" -> new")
    public static RequestSearch of(){
        return new RequestSearch();
    }

    @NotNull
    public static RequestSearch of(FilterValueEnum filterValueEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setSearchFilter(SearchFilter.of(filterValueEnum));
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(DirectionEnum directionEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setSort(SearchSort.of(directionEnum));
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(FilterValueEnum filterValueEnum, DirectionEnum directionEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setSort(SearchSort.of(directionEnum));
        requestSearch.setSearchFilter(SearchFilter.of(filterValueEnum));
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(String title){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setQuery(title);
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(String title, FilterValueEnum filterValueEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setQuery(title);
        requestSearch.setSearchFilter(SearchFilter.of(filterValueEnum));
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(String title, DirectionEnum directionEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setQuery(title);
        requestSearch.setSort(SearchSort.of(directionEnum));
        return requestSearch;
    }

    @NotNull
    public static RequestSearch of(String title, FilterValueEnum filterValueEnum, DirectionEnum directionEnum){
        RequestSearch requestSearch = new RequestSearch();
        requestSearch.setQuery(title);
        requestSearch.setSort(SearchSort.of(directionEnum));
        requestSearch.setSearchFilter(SearchFilter.of(filterValueEnum));
        return requestSearch;
    }
}
