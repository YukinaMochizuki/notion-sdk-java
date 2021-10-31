package tw.yukina.notion.sdk.model.block.heading;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Heading {
    private static final String TEXT_FIELD = "text";

    @JsonProperty(TEXT_FIELD)
    private List<RichText> texts;
}
