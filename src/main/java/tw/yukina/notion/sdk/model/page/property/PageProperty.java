package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.common.Property;
import tw.yukina.notion.sdk.model.deserializer.PagePropertyDeserializer;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = PagePropertyDeserializer.class)
public class PageProperty extends Property {
}
