package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LinkPreview {

    private static final String URL_FIELD = "url";

    @JsonProperty(URL_FIELD)
    private String url;
}
