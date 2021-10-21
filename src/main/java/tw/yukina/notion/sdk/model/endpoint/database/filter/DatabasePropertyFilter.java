package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DatabasePropertyFilter {

    public static final String PROPERTY_FIELD = "property";

    @JsonProperty(PROPERTY_FIELD)
    private String property;
}
