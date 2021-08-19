package tw.yukina.notion.sdk.model.common.unit;

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
public class Database {
    private static final String ID_FIELD = "id";

    @JsonProperty(ID_FIELD)
    private String databaseId;
}
