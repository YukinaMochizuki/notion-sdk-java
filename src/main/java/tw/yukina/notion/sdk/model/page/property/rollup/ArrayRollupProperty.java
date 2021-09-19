package tw.yukina.notion.sdk.model.page.property.rollup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ArrayRollupProperty extends RollupObject {

    private static final String ARRAY_FIELD = "array";

    @JsonProperty(ARRAY_FIELD)
    private List<PageProperty> pageProperties;

}
