package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class PhonePropertyFilter extends DatabasePropertyFilter {

    public static final String PHONE_FIELD = "phone";

    @JsonProperty(PHONE_FIELD)
    private TextFilterObject textFilterObject;

    public static PhonePropertyFilter of(String property, TextFilterObject textFilterObject){
        PhonePropertyFilter phonePropertyFilter = new PhonePropertyFilter();
        phonePropertyFilter.setName(property);
        phonePropertyFilter.setTextFilterObject(textFilterObject);
        return phonePropertyFilter;
    }
}
