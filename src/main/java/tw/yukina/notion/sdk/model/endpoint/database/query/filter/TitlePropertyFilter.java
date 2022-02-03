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
public class TitlePropertyFilter extends DatabasePropertyFilter {

    public static final String TITLE_FIELD = "title";

    @JsonProperty(TITLE_FIELD)
    private TextFilterObject textFilterObject;

    @NotNull
    public static TitlePropertyFilter of(TextFilterObject textFilterObject){
        TitlePropertyFilter titlePropertyFilter = new TitlePropertyFilter();
        titlePropertyFilter.setTextFilterObject(textFilterObject);
        titlePropertyFilter.setName("title");
        return titlePropertyFilter;
    }
}
