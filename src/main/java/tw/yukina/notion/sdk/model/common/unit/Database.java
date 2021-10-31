package tw.yukina.notion.sdk.model.common.unit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Database {
    private static final String ID_FIELD = "id";

    @JsonProperty(ID_FIELD)
    private String databaseId;
}
