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
public class Bookmark {

    private static final String URL_FIELD = "url";
    private static final String CAPTION_FIELD = "caption";

    @JsonProperty(URL_FIELD)
    private String url;

    @JsonProperty(CAPTION_FIELD)
    private List<RichText> richTexts;

}
