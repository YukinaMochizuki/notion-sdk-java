package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.Color;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Annotation {
    private static final String BOLD_FIELD = "bold";
    private static final String ITALIC_FIELD = "italic";
    private static final String STRIKETHROUGH_FIELD = "strikethrough";
    private static final String UNDERLINE_FIELD = "underline";
    private static final String CODE_FIELD = "code";
    private static final String COLOR_FIELD = "color";

    @JsonProperty(BOLD_FIELD)
    private boolean isBold;

    @JsonProperty(ITALIC_FIELD)
    private boolean isItalic;

    @JsonProperty(STRIKETHROUGH_FIELD)
    private boolean isStrikethrough;

    @JsonProperty(UNDERLINE_FIELD)
    private boolean isUnderline;

    @JsonProperty(CODE_FIELD)
    private boolean isCode;

    @JsonProperty(COLOR_FIELD)
    private Color color;

    public void setAllDefault(){
        isBold = false;
        isItalic = false;
        isStrikethrough = false;
        isUnderline = false;
        isCode = false;
        color = Color.DEFAULT;
    }

}
