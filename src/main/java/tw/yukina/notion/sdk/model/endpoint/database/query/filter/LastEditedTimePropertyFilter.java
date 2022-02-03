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
public class LastEditedTimePropertyFilter extends DatabasePropertyFilter {

    public static final String DATE_FIELD = "last_edited_time";

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static LastEditedTimePropertyFilter of(String property, DateFilterObject dateFilterObject){
        LastEditedTimePropertyFilter lastEditedTimePropertyFilter = new LastEditedTimePropertyFilter();
        lastEditedTimePropertyFilter.setName(property);
        lastEditedTimePropertyFilter.setDateFilterObject(dateFilterObject);
        return lastEditedTimePropertyFilter;
    }
}
