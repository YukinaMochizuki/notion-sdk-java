package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.FormulaDeserializer;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = FormulaDeserializer.class)
public class FormulaObject {

    private static final String TYPE_FIELD = "type";

    @JsonProperty(TYPE_FIELD)
    private FormulaType formulaType;

}
