package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EmailPropertyFilter extends DatabasePropertyFilter {

    public static final String EMAIL_FIELD = "email";

    @JsonProperty(EMAIL_FIELD)
    private TextFilterObject textFilterObject;
}
