package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class MultiSelectProperty extends PageProperty {

    private static final String MULTI_SELECT_FIELD = "multi_select";

    @JsonProperty(MULTI_SELECT_FIELD)
    private List<SelectOption> selectOptions;

    @NotNull
    public static MultiSelectProperty of(String ...options){
        List<SelectOption> selectOptions = new ArrayList<>();

        for(String option: options){
            SelectOption selectOption = new SelectOption();
            selectOption.setName(option);
            selectOptions.add(selectOption);
        }

        MultiSelectProperty multiSelectProperty = new MultiSelectProperty();
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        multiSelectProperty.setSelectOptions(selectOptions);
        return multiSelectProperty;
    }
}
