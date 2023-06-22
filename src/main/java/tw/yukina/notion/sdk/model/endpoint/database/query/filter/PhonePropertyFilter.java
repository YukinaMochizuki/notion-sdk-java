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

    public static PhonePropertyFilter of(String property, TextFilterObject textFilterObject) {
        PhonePropertyFilter phonePropertyFilter = new PhonePropertyFilter();
        phonePropertyFilter.setName(property);
        phonePropertyFilter.setTextFilterObject(textFilterObject);
        return phonePropertyFilter;
    }

    public static PhonePropertyFilter equalsOf(String property, String equals) {
        return of(property, TextFilterObject.equalsOf(equals));
    }

    public static PhonePropertyFilter doesNotEqualOf(String property, String doesNotEqual) {
        return of(property, TextFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static PhonePropertyFilter containsOf(String property, String contains) {
        return of(property, TextFilterObject.containsOf(contains));
    }

    public static PhonePropertyFilter doesNotContainOf(String property, String doesNotContain) {
        return of(property, TextFilterObject.doesNotContainOf(doesNotContain));
    }

    public static PhonePropertyFilter startsWithOf(String property, String startsWith) {
        return of(property, TextFilterObject.startsWithOf(startsWith));
    }

    public static PhonePropertyFilter endsWithOf(String property, String endsWith) {
        return of(property, TextFilterObject.endsWithOf(endsWith));
    }

    public static PhonePropertyFilter isEmptyOf(String property, boolean isEmpty) {
        return of(property, TextFilterObject.isEmptyOf(isEmpty));
    }

    public static PhonePropertyFilter isNotEmptyOf(String property, boolean isNotEmpty) {
        return of(property, TextFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
