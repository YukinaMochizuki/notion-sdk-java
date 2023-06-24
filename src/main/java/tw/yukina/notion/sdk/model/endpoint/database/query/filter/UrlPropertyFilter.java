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
    public static UrlPropertyFilter of(String property, TextFilterObject textFilterObject) {
        UrlPropertyFilter urlPropertyFilter = new UrlPropertyFilter();
        urlPropertyFilter.setName(property);
        urlPropertyFilter.setTextFilterObject(textFilterObject);
        return urlPropertyFilter;
    }

    public static UrlPropertyFilter equalsOf(String property, String equals) {
        return of(property, TextFilterObject.equalsOf(equals));
    }

    public static UrlPropertyFilter doesNotEqualOf(String property, String doesNotEqual) {
        return of(property, TextFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static UrlPropertyFilter containsOf(String property, String contains) {
        return of(property, TextFilterObject.containsOf(contains));
    }

    public static UrlPropertyFilter doesNotContainOf(String property, String doesNotContain) {
        return of(property, TextFilterObject.doesNotContainOf(doesNotContain));
    }

    public static UrlPropertyFilter startsWithOf(String property, String startsWith) {
        return of(property, TextFilterObject.startsWithOf(startsWith));
    }

    public static UrlPropertyFilter endsWithOf(String property, String endsWith) {
        return of(property, TextFilterObject.endsWithOf(endsWith));
    }

    public static UrlPropertyFilter isEmptyOf(String property, boolean isEmpty) {
        return of(property, TextFilterObject.isEmptyOf(isEmpty));
    }

    public static UrlPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty) {
        return of(property, TextFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
