package tw.yukina.notion.sdk.model.block.heading;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class HeadingOneBlock extends Block implements HeadingBlock{
    private static final String HEADING_ONE_FIELD = "heading_1";

    @JsonProperty(HEADING_ONE_FIELD)
    private Heading heading;
}
