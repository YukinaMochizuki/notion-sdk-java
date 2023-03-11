package tw.yukina.notion.sdk.model.common.date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.serializer.LocalDateSerializer;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class Date extends DateTimeProperty {
    private static final String START_FIELD = "start";
    private static final String END_FIELD = "end";
    private static final String TIME_ZONE_FIELD = "time_zone";

    @JsonProperty(START_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate start;

    @Nullable
    @JsonProperty(END_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate end;

    @Nullable
    @JsonProperty(TIME_ZONE_FIELD)
    private String timeZone;

    @NotNull
    public static Date of(LocalDate start){
        Date date = new Date();
        date.setDateTimeType(DateTimeType.DATE);
        date.setStart(start);
        return date;
    }

    @NotNull
    public static Date of(LocalDate start, LocalDate end){
        Date date = new Date();
        date.setDateTimeType(DateTimeType.DATE);
        date.setStart(start);
        date.setEnd(end);
        return date;
    }

    @Override
    public String startToString() {
        return start.toString();
    }

    @Override
    public String endToString() {
        return end != null ? end.toString() : "";
    }

}
