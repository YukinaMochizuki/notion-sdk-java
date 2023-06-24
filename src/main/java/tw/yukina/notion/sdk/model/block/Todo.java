package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

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

    public Todo(@NotNull Paragraph paragraph) {
        this.setChildren(paragraph.getChildren());
        this.setRichTexts(paragraph.getRichTexts());
    }
}
