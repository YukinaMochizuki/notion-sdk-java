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
public class LastEditedTimePropertyFilter extends DatabasePropertyFilter {

    public static final String DATE_FIELD = "last_edited_time";

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static LastEditedTimePropertyFilter of(String property, DateFilterObject dateFilterObject) {
        LastEditedTimePropertyFilter lastEditedTimePropertyFilter = new LastEditedTimePropertyFilter();
        lastEditedTimePropertyFilter.setName(property);
        lastEditedTimePropertyFilter.setDateFilterObject(dateFilterObject);
        return lastEditedTimePropertyFilter;
    }

    public static LastEditedTimePropertyFilter ofEquals(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofEquals(zonedDateTime));
    }

    public static LastEditedTimePropertyFilter ofBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofBefore(zonedDateTime));
    }

    public static LastEditedTimePropertyFilter ofAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofAfter(zonedDateTime));
    }

    public static LastEditedTimePropertyFilter ofOnOrBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrBefore(zonedDateTime));
    }

    public static LastEditedTimePropertyFilter ofIsEmpty(String property) {
        return of(property, DateFilterObject.ofIsEmpty());
    }

    public static LastEditedTimePropertyFilter ofIsNotEmpty(String property) {
        return of(property, DateFilterObject.ofIsNotEmpty());
    }

    public static LastEditedTimePropertyFilter ofOnOrAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrAfter(zonedDateTime));
    }

    public static LastEditedTimePropertyFilter ofPastWeek(String property) {
        return of(property, DateFilterObject.ofPastWeek());
    }

    public static LastEditedTimePropertyFilter ofPastMonth(String property) {
        return of(property, DateFilterObject.ofPastMonth());
    }

    public static LastEditedTimePropertyFilter ofPastYear(String property) {
        return of(property, DateFilterObject.ofPastYear());
    }

    public static LastEditedTimePropertyFilter ofNextWeek(String property) {
        return of(property, DateFilterObject.ofNextWeek());
    }

    public static LastEditedTimePropertyFilter ofNextMonth(String property) {
        return of(property, DateFilterObject.ofNextMonth());
    }

    public static LastEditedTimePropertyFilter ofNextYear(String property) {
        return of(property, DateFilterObject.ofNextYear());
    }
}
