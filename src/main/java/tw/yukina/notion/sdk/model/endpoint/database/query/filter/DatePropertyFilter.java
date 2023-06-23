package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class DatePropertyFilter extends DatabasePropertyFilter {

    public static final String DATE_FIELD = "date";

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static DatePropertyFilter of(String property, DateFilterObject dateFilterObject) {
        DatePropertyFilter datePropertyFilter = new DatePropertyFilter();
        datePropertyFilter.setName(property);
        datePropertyFilter.setDateFilterObject(dateFilterObject);
        return datePropertyFilter;
    }

    public static DatePropertyFilter ofEquals(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofEquals(zonedDateTime));
    }

    public static DatePropertyFilter ofBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofBefore(zonedDateTime));
    }

    public static DatePropertyFilter ofAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofAfter(zonedDateTime));
    }

    public static DatePropertyFilter ofOnOrBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrBefore(zonedDateTime));
    }

    public static DatePropertyFilter ofIsEmpty(String property) {
        return of(property, DateFilterObject.ofIsEmpty());
    }

    public static DatePropertyFilter ofIsNotEmpty(String property) {
        return of(property, DateFilterObject.ofIsNotEmpty());
    }

    public static DatePropertyFilter ofOnOrAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrAfter(zonedDateTime));
    }

    public static DatePropertyFilter ofPastWeek(String property) {
        return of(property, DateFilterObject.ofPastWeek());
    }

    public static DatePropertyFilter ofPastMonth(String property) {
        return of(property, DateFilterObject.ofPastMonth());
    }

    public static DatePropertyFilter ofPastYear(String property) {
        return of(property, DateFilterObject.ofPastYear());
    }

    public static DatePropertyFilter ofNextWeek(String property) {
        return of(property, DateFilterObject.ofNextWeek());
    }

    public static DatePropertyFilter ofNextMonth(String property) {
        return of(property, DateFilterObject.ofNextMonth());
    }

    public static DatePropertyFilter ofNextYear(String property) {
        return of(property, DateFilterObject.ofNextYear());
    }
}
