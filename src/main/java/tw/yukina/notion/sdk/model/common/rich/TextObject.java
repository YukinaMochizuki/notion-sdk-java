package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TextObject {
    private static final String CONTENT_FIELD = "content";
    private static final String LINK_FIELD = "link";

    @JsonProperty(CONTENT_FIELD)
    private String content;

    @JsonProperty(LINK_FIELD)
    @Nullable
    private Link link;
}
