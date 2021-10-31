package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChildPage {
    private static final String TITLE_FIELD = "title";

    @JsonProperty(TITLE_FIELD)
    private String title;
}
