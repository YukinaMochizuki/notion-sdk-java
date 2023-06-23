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
public class NumberPropertyFilter extends DatabasePropertyFilter {

    public static final String NUMBER_FIELD = "number";

    @JsonProperty(NUMBER_FIELD)
    private NumberFilterObject numberFilterObject;

    @NotNull
    public static NumberPropertyFilter of(String property, NumberFilterObject numberFilterObject) {
        NumberPropertyFilter numberPropertyFilter = new NumberPropertyFilter();
        numberPropertyFilter.setName(property);
        numberPropertyFilter.setNumberFilterObject(numberFilterObject);
        return numberPropertyFilter;
    }

    public static NumberPropertyFilter equalsOf(String property, Double equals) {
        return of(property, NumberFilterObject.equalsOf(equals));
    }

    public static NumberPropertyFilter doesNotEqualOf(String property, Double doesNotEqual) {
        return of(property, NumberFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static NumberPropertyFilter containsOf(String property, Double contains) {
        return of(property, NumberFilterObject.containsOf(contains));
    }

    public static NumberPropertyFilter doesNotContainOf(String property, Double doesNotContain) {
        return of(property, NumberFilterObject.doesNotContainOf(doesNotContain));
    }

    public static NumberPropertyFilter startsWithOf(String property, Double startsWith) {
        return of(property, NumberFilterObject.startsWithOf(startsWith));
    }

    public static NumberPropertyFilter endsWithOf(String property, Double endsWith) {
        return of(property, NumberFilterObject.endsWithOf(endsWith));
    }

    public static NumberPropertyFilter isEmptyOf(String property, boolean isEmpty) {
        return of(property, NumberFilterObject.isEmptyOf(isEmpty));
    }

    public static NumberPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty) {
        return of(property, NumberFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
