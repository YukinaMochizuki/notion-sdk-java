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

    public static EmailPropertyFilter equalsOf(String property, String equals){
        return of(property, TextFilterObject.equalsOf(equals));
    }

    public static EmailPropertyFilter doesNotEqualOf(String property, String doesNotEqual){
        return of(property, TextFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static EmailPropertyFilter containsOf(String property, String contains){
        return of(property, TextFilterObject.containsOf(contains));
    }

    public static EmailPropertyFilter doesNotContainOf(String property, String doesNotContain){
        return of(property, TextFilterObject.doesNotContainOf(doesNotContain));
    }

    public static EmailPropertyFilter startsWithOf(String property, String startsWith){
        return of(property, TextFilterObject.startsWithOf(startsWith));
    }

    public static EmailPropertyFilter endsWithOf(String property, String endsWith){
        return of(property, TextFilterObject.endsWithOf(endsWith));
    }

    public static EmailPropertyFilter isEmptyOf(String property, boolean isEmpty){
        return of(property, TextFilterObject.isEmptyOf(isEmpty));
    }

    public static EmailPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty){
        return of(property, TextFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
