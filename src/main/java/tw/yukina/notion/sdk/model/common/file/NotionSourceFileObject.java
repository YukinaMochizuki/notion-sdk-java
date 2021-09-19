package tw.yukina.notion.sdk.model.common.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.net.URL;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotionSourceFileObject {

    private static final String URL_FIELD = "url";
    private static final String EXPIRY_TIME_FIELD = "expiry_time";

    @JsonProperty(URL_FIELD)
    private URL url;

    @JsonProperty(EXPIRY_TIME_FIELD)
    private ZonedDateTime expiryTime;

}
