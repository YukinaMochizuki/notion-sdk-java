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

    private static final String TEXT_FIELD = "text";
    private static final String LANGUAGE_FIELD = "language";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> richTexts;

    @JsonProperty(LANGUAGE_FIELD)
    private CodeLanguageType languageType;

}
