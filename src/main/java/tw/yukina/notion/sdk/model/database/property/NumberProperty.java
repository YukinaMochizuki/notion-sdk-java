package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class NumberProperty extends DatabaseProperty {

    private static final String NUMBER_FIELD = "number";

    @JsonProperty(NUMBER_FIELD)
    private NumberObject numberObject;

    @NotNull
    public static NumberProperty of(String name, NumberFormat numberFormat){
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(numberFormat);
        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setName(name);
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumberObject(numberObject);
        return numberProperty;
    }
}
