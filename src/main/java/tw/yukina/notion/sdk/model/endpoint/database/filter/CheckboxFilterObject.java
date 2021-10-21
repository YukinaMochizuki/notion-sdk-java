package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CheckboxFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String DOES_NOT_EQUAL_FIELD = "does_not_equal";

    @JsonProperty(EQUALS_FIELD)
    private boolean equals;

    @JsonProperty(DOES_NOT_EQUAL_FIELD)
    private boolean doesNotEqual;
}
