package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.TextColor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TableOfContents {
    private static final String COLOR_FIELD = "color";

    @JsonProperty(COLOR_FIELD)
    private TextColor color;
}
