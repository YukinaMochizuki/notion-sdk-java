package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.date.Date;
import tw.yukina.notion.sdk.model.common.date.DateTime;
import tw.yukina.notion.sdk.model.common.date.DateTimeProperty;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DateProperty extends PageProperty {

    private static final String DATE_FIELD = "date";

    @JsonProperty(DATE_FIELD)
    private DateTimeProperty dateTimeProperty;

    @NotNull
    public static DateProperty of(LocalDate start){
        Date date = Date.of(start);
        return DateProperty.of(date);
    }

    @NotNull
    public static DateProperty of(LocalDate start, LocalDate end){
        Date date = Date.of(start, end);
        return DateProperty.of(date);
    }

    @NotNull
    public static DateProperty of(ZonedDateTime start){
        DateTime dateTime = DateTime.of(start);
        return DateProperty.of(dateTime);
    }

    @NotNull
    public static DateProperty of(ZonedDateTime start, ZonedDateTime end){
        DateTime dateTime = DateTime.of(start, end);
        return DateProperty.of(dateTime);
    }

    @NotNull
    public static DateProperty of(DateTimeProperty dateTimeProperty){
        DateProperty dateProperty = new DateProperty();
        dateProperty.setType(PropertyType.DATE);
        dateProperty.setDateTimeProperty(dateTimeProperty);
        return dateProperty;
    }
}
