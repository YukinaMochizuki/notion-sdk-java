package tw.yukina.notion.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.OptionColor;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SelectOption {

    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String COLOR_FIELD = "color";

    @JsonProperty(ID_FIELD)
    private String id;

    @JsonProperty(NAME_FIELD)
    private String name;

    @JsonProperty(COLOR_FIELD)
    private OptionColor color;

    @NotNull
    public static SelectOption of(String name) {
        SelectOption selectOption = new SelectOption();
        selectOption.setName(name);
        selectOption.setColor(OptionColor.randomColor());
        return selectOption;
    }
}
