package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class SelectProperty extends PageProperty {

    private static final String SELECT_FIELD = "select";

    @JsonProperty(SELECT_FIELD)
    private SelectOption selectOption;

    @NotNull
    public static SelectProperty of(String option) {
        SelectOption selectOption = new SelectOption();
        selectOption.setName(option);
        SelectProperty selectProperty = new SelectProperty();
        selectProperty.setType(PropertyType.SELECT);
        selectProperty.setSelectOption(selectOption);
        return selectProperty;
    }
}
