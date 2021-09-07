package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NumberObject {
    private static final String FORMAT_FIELD = "format";

    @JsonProperty(FORMAT_FIELD)
    private NumberFormat numberFormat;
}
