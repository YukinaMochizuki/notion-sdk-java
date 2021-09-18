package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.block.list.ListObject;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends ListObject {
    private static final String CHECKED_FIELD = "checked";

    @JsonProperty(CHECKED_FIELD)
    private boolean checked = false;

}
