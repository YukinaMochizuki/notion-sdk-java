package tw.yukina.notion.sdk.model.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Date {
    private static final String START_FIELD = "start";
    private static final String END_FIELD = "end";

    @JsonProperty(START_FIELD)
    private ZonedDateTime start;

    @JsonProperty(END_FIELD)
    @Nullable
    private ZonedDateTime end;
}
