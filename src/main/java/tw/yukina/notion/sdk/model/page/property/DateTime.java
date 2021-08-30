package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.Nullable;

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
    private ZonedDateTime start;

    @JsonProperty(END_FIELD)
    @Nullable
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
