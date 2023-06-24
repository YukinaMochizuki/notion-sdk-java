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
    public static TitlePropertyFilter of(TextFilterObject textFilterObject) {
        TitlePropertyFilter titlePropertyFilter = new TitlePropertyFilter();
        titlePropertyFilter.setTextFilterObject(textFilterObject);
        titlePropertyFilter.setName("title");
        return titlePropertyFilter;
    }

    public static TitlePropertyFilter equalsOf(String equals) {
        return of(TextFilterObject.equalsOf(equals));
    }

    public static TitlePropertyFilter doesNotEqualOf(String doesNotEqual) {
        return of(TextFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static TitlePropertyFilter containsOf(String contains) {
        return of(TextFilterObject.containsOf(contains));
    }

    public static TitlePropertyFilter doesNotContainOf(String doesNotContain) {
        return of(TextFilterObject.doesNotContainOf(doesNotContain));
    }

    public static TitlePropertyFilter startsWithOf(String startsWith) {
        return of(TextFilterObject.startsWithOf(startsWith));
    }

    public static TitlePropertyFilter endsWithOf(String endsWith) {
        return of(TextFilterObject.endsWithOf(endsWith));
    }

    public static TitlePropertyFilter isEmptyOf(boolean isEmpty) {
        return of(TextFilterObject.isEmptyOf(isEmpty));
    }

    public static TitlePropertyFilter isNotEmptyOf(boolean isNotEmpty) {
        return of(TextFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
