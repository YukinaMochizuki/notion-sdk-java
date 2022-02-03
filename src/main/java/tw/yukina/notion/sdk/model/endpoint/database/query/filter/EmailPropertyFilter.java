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
public class EmailPropertyFilter extends DatabasePropertyFilter {

    public static final String EMAIL_FIELD = "email";

    @JsonProperty(EMAIL_FIELD)
    private TextFilterObject textFilterObject;

    @NotNull
    public static EmailPropertyFilter of(String property, TextFilterObject textFilterObject){
        EmailPropertyFilter emailPropertyFilter = new EmailPropertyFilter();
        emailPropertyFilter.setName(property);
        emailPropertyFilter.setTextFilterObject(textFilterObject);
        return emailPropertyFilter;
    }
}
