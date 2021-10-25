package tw.yukina.notion.sdk.model.common.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.serializer.UserZonedDateTimeSerializer;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DateTime extends DateTimeProperty{
    private static final String START_FIELD = "start";
    private static final String END_FIELD = "end";

    @JsonProperty(START_FIELD)
    @JsonSerialize(using = UserZonedDateTimeSerializer.class)
    private ZonedDateTime start;

    @Nullable
    @JsonProperty(END_FIELD)
    @JsonSerialize(using = UserZonedDateTimeSerializer.class)
    private ZonedDateTime end;

    @NotNull
    public static DateTime of(ZonedDateTime start) {
        DateTime dateTime = new DateTime();
        dateTime.setDateTimeType(DateTimeType.DATE_TIME);
        dateTime.setStart(start);
        return dateTime;
    }

    @NotNull
    public static DateTime of(ZonedDateTime start, ZonedDateTime end) {
        DateTime dateTime = new DateTime();
        dateTime.setDateTimeType(DateTimeType.DATE_TIME);
        dateTime.setStart(start);
        dateTime.setEnd(end);
        return dateTime;
    }

    @Override
    public String startToString() {
        return start.toString();
    }

    @Nullable
    @Override
    public String endToString() {
        return end != null ? end.toString() : "";
    }
}
