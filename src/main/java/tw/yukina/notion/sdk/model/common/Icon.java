package tw.yukina.notion.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.IconDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonDeserialize(using = IconDeserializer.class)
public abstract class Icon {

    private static final String TYPE_FIELD = "type";

    @JsonProperty(TYPE_FIELD)
    private String type;

}
