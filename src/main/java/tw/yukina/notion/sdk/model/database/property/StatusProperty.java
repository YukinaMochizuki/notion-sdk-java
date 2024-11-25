package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class StatusProperty extends DatabaseProperty {

    private static final String STATUS = "status";

    @JsonProperty(STATUS)
    private SelectObject selectObject;

    @NotNull
    public static StatusProperty of(String name, @NotNull String... options) {
        SelectObject selectObject = new SelectObject();
        List<SelectOption> selectOptions = new ArrayList<>();
        selectObject.setSelectOptions(selectOptions);
        for (String option : options) selectOptions.add(SelectOption.of(option));

        StatusProperty multiSelectProperty = new StatusProperty();
        multiSelectProperty.setName(name);
        multiSelectProperty.setType(PropertyType.STATUS);
        multiSelectProperty.setSelectObject(selectObject);
        return multiSelectProperty;
    }
}
