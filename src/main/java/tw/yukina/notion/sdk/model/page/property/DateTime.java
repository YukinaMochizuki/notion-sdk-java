package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.serializer.UserZonedDateTimeSerializer;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @Override
    public String startToString() {
        return start.toString();
    }

    @Nullable
    @Override
    public String endToString() {
        return Objects.requireNonNull(end).toString();
    }
}
