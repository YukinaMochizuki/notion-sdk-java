package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class Text extends RichText{

    private static final String TEXT_FIELD = "text";

    @JsonProperty(TEXT_FIELD)
    private TextObject textObject;
}
