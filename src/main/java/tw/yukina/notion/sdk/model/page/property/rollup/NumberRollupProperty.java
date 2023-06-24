package tw.yukina.notion.sdk.model.page.property.rollup;

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
public class NumberRollupProperty extends RollupObject {

    private static final String NUMBER_FIELD = "number";

    @JsonProperty(NUMBER_FIELD)
    private double number;

}
