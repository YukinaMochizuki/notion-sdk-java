package tw.yukina.notion.sdk.model.block.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.net.URL;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalObject {

    private static final String URL_FIELD = "url";

    @JsonProperty(URL_FIELD)
    private URL url;

}
