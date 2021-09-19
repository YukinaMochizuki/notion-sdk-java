package tw.yukina.notion.sdk.model.page.property.rollup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.RollupDeserializer;

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = RollupDeserializer.class)
public class RollupObject {

    private static final String TYPE_FIELD = "type";

    @JsonProperty(TYPE_FIELD)
    private RollupType rollupType;

}
