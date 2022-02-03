package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class MultiSelectPropertyFilter extends DatabasePropertyFilter {

    public static final String MULTI_SELECT_FIELD = "multi_select";

    @JsonProperty(MULTI_SELECT_FIELD)
    private SelectFilterObject selectFilterObject;

    @NotNull
    public static MultiSelectPropertyFilter of(String property, SelectFilterObject selectFilterObject){
        MultiSelectPropertyFilter multiSelectPropertyFilter = new MultiSelectPropertyFilter();
        multiSelectPropertyFilter.setName(property);
        multiSelectPropertyFilter.setSelectFilterObject(selectFilterObject);
        return multiSelectPropertyFilter;
    }
}
