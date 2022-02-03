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
public class UrlPropertyFilter extends DatabasePropertyFilter {

    public static final String URL_FIELD = "url";

    @JsonProperty(URL_FIELD)
    private TextFilterObject textFilterObject;

    @NotNull
    public static UrlPropertyFilter of(String property, TextFilterObject textFilterObject){
        UrlPropertyFilter urlPropertyFilter = new UrlPropertyFilter();
        urlPropertyFilter.setName(property);
        urlPropertyFilter.setTextFilterObject(textFilterObject);
        return urlPropertyFilter;
    }
}
