package tw.yukina.notion.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IconFileObject {

    private static final String URL_FIELD = "url";
    private static final String EXPIRY_FIELD = "expiry_time";

    @JsonProperty(URL_FIELD)
    private String url;

    @JsonProperty(EXPIRY_FIELD)
    private ZonedDateTime expiryTime;

}
