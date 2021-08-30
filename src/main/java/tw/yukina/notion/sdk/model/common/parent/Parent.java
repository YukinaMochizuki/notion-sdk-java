package tw.yukina.notion.sdk.model.common.parent;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.ParentDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = ParentDeserializer.class)
public class Parent {
    private ParentType parentType;
}
