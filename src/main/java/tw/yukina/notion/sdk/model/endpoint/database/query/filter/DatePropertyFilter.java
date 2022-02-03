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
public class DatePropertyFilter extends DatabasePropertyFilter {

    public static final String DATE_FIELD = "date";

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static DatePropertyFilter of(String property, DateFilterObject dateFilterObject){
        DatePropertyFilter datePropertyFilter = new DatePropertyFilter();
        datePropertyFilter.setName(property);
        datePropertyFilter.setDateFilterObject(dateFilterObject);
        return datePropertyFilter;
    }
}
