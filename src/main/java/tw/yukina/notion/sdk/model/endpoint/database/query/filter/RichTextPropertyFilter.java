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
public class RichTextPropertyFilter extends DatabasePropertyFilter {

    public static final String RICH_TEXT_FIELD = "rich_text";

    @JsonProperty(RICH_TEXT_FIELD)
    private TextFilterObject textFilterObject;

    @NotNull
    public static RichTextPropertyFilter of(String property, TextFilterObject textFilterObject){
        RichTextPropertyFilter richTextPropertyFilter = new RichTextPropertyFilter();
        richTextPropertyFilter.setName(property);
        richTextPropertyFilter.setTextFilterObject(textFilterObject);
        return richTextPropertyFilter;
    }
}
