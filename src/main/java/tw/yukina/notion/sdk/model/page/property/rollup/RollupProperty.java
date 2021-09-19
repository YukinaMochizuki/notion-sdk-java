package tw.yukina.notion.sdk.model.page.property.rollup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RollupProperty extends PageProperty {

    private static final String ROLLUP_FIELD = "rollup";

    @JsonProperty(ROLLUP_FIELD)
    private RollupObject rollup;

}
