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
public class NumberPropertyFilter extends DatabasePropertyFilter {

    public static final String NUMBER_FIELD = "number";

    @JsonProperty(NUMBER_FIELD)
    private NumberFilterObject numberFilterObject;

    @NotNull
    public static NumberPropertyFilter of(String property, NumberFilterObject numberFilterObject){
        NumberPropertyFilter numberPropertyFilter = new NumberPropertyFilter();
        numberPropertyFilter.setName(property);
        numberPropertyFilter.setNumberFilterObject(numberFilterObject);
        return numberPropertyFilter;
    }
}
