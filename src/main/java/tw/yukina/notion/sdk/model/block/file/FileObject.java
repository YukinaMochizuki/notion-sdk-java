package tw.yukina.notion.sdk.model.block.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileObject {

    private static final String TYPE_FIELD = "type";
    private static final String EXTERNAL_FIELD = "external";

    @JsonProperty(TYPE_FIELD)
    private String type = "external";

    @JsonProperty(EXTERNAL_FIELD)
    private ExternalObject externalObject;
}
