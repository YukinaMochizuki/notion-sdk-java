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
public class CreatedTimePropertyFilter extends DatabasePropertyFilter {

    public static final String DATE_FIELD = "created_time";

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;

    @NotNull
    public static CreatedTimePropertyFilter of(String property, DateFilterObject dateFilterObject) {
        CreatedTimePropertyFilter createdTimePropertyFilter = new CreatedTimePropertyFilter();
        createdTimePropertyFilter.setName(property);
        createdTimePropertyFilter.setDateFilterObject(dateFilterObject);
        return createdTimePropertyFilter;
    }

    public static CreatedTimePropertyFilter ofEquals(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofEquals(zonedDateTime));
    }

    public static CreatedTimePropertyFilter ofBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofBefore(zonedDateTime));
    }

    public static CreatedTimePropertyFilter ofAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofAfter(zonedDateTime));
    }

    public static CreatedTimePropertyFilter ofOnOrBefore(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrBefore(zonedDateTime));
    }

    public static CreatedTimePropertyFilter ofIsEmpty(String property) {
        return of(property, DateFilterObject.ofIsEmpty());
    }

    public static CreatedTimePropertyFilter ofIsNotEmpty(String property) {
        return of(property, DateFilterObject.ofIsNotEmpty());
    }

    public static CreatedTimePropertyFilter ofOnOrAfter(String property, ZonedDateTime zonedDateTime) {
        return of(property, DateFilterObject.ofOnOrAfter(zonedDateTime));
    }

    public static CreatedTimePropertyFilter ofPastWeek(String property) {
        return of(property, DateFilterObject.ofPastWeek());
    }

    public static CreatedTimePropertyFilter ofPastMonth(String property) {
        return of(property, DateFilterObject.ofPastMonth());
    }

    public static CreatedTimePropertyFilter ofPastYear(String property) {
        return of(property, DateFilterObject.ofPastYear());
    }

    public static CreatedTimePropertyFilter ofNextWeek(String property) {
        return of(property, DateFilterObject.ofNextWeek());
    }

    public static CreatedTimePropertyFilter ofNextMonth(String property) {
        return of(property, DateFilterObject.ofNextMonth());
    }

    public static CreatedTimePropertyFilter ofNextYear(String property) {
        return of(property, DateFilterObject.ofNextYear());
    }
}
