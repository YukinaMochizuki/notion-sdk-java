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
public class NumberProperty extends PageProperty {

    private static final String NUMBER_FIELD = "number";

    @JsonProperty(NUMBER_FIELD)
    private double number;

    @NotNull
    public static NumberProperty of(double number) {
        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumber(number);
        return numberProperty;
    }
}
