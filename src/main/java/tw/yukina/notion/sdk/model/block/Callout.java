package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.common.Icon;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Callout extends Paragraph {

    private static final String ICON_FIELD = "icon";

    @JsonProperty(ICON_FIELD)
    private Icon icon;
}
