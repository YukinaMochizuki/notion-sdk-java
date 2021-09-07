package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectObject {
    private static final String OPTIONS_FIELD = "options";

    @JsonProperty(OPTIONS_FIELD)
    private List<SelectOption> selectOptions;

}
