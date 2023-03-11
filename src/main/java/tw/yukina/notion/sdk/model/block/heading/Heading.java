package tw.yukina.notion.sdk.model.block.heading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.deserializer.HeadingDeserializer;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = HeadingDeserializer.class)
public class Heading {
    private static final String RICH_TEXT_FIELD = "rich_text";

    private static final String COLOR_FIELD = "color";

    private static final String IS_TOGGLEABLE_FIELD = "is_toggleable";

    @JsonProperty(RICH_TEXT_FIELD)
    private List<RichText> richTexts;

    @JsonProperty(COLOR_FIELD)
    private TextColor color;

    @JsonProperty(IS_TOGGLEABLE_FIELD)
    private boolean isToggleable;
}
