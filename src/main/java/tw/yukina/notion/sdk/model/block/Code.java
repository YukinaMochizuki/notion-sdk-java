package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Code {

    private static final String RICH_TEXT_FIELD = "rich_text";
    private static final String LANGUAGE_FIELD = "language";
    private static final String CAPTION_FIELD = "caption";

    @JsonProperty(RICH_TEXT_FIELD)
    private List<RichText> richTexts;

    @JsonProperty(LANGUAGE_FIELD)
    private CodeLanguageType languageType;

    @JsonProperty(CAPTION_FIELD)
    private List<RichText> caption;
}
