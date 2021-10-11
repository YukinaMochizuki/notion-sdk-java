package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends Paragraph {
    private static final String CHECKED_FIELD = "checked";

    @JsonProperty(CHECKED_FIELD)
    private boolean checked = false;

}
