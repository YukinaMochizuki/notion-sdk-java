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
    
    public static RichTextPropertyFilter equalsOf(String property, String equals){
        return of(property, TextFilterObject.equalsOf(equals));
    }
    
    public static RichTextPropertyFilter doesNotEqualOf(String property, String doesNotEqual){
        return of(property, TextFilterObject.doesNotEqualOf(doesNotEqual));
    }
    
    public static RichTextPropertyFilter containsOf(String property, String contains){
        return of(property, TextFilterObject.containsOf(contains));
    }
    
    public static RichTextPropertyFilter doesNotContainOf(String property, String doesNotContain){
        return of(property, TextFilterObject.doesNotContainOf(doesNotContain));
    }
    
    public static RichTextPropertyFilter startsWithOf(String property, String startsWith){
        return of(property, TextFilterObject.startsWithOf(startsWith));
    }
    
    public static RichTextPropertyFilter endsWithOf(String property, String endsWith){
        return of(property, TextFilterObject.endsWithOf(endsWith));
    }
    
    public static RichTextPropertyFilter isEmptyOf(String property, boolean isEmpty){
        return of(property, TextFilterObject.isEmptyOf(isEmpty));
    }
    
    public static RichTextPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty){
        return of(property, TextFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
