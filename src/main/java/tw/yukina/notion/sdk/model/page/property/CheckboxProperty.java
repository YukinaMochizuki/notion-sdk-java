package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class CheckboxProperty extends PageProperty {

    private static final String CHECKBOX_FIELD = "checkbox";

    @JsonProperty(CHECKBOX_FIELD)
    private boolean checkbox;

    @NotNull
    public static CheckboxProperty of(String id, boolean checkbox){
        CheckboxProperty checkboxProperty = new CheckboxProperty();
        checkboxProperty.setId(id);
        checkboxProperty.setType(PropertyType.CHECKBOX);
        checkboxProperty.setCheckbox(checkbox);
        return checkboxProperty;
    }
}