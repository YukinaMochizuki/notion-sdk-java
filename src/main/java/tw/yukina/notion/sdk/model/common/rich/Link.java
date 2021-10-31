package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.net.URL;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Link {
    private static final String URL_FIELD = "url";

    @JsonProperty(URL_FIELD)
    private URL url;
}
