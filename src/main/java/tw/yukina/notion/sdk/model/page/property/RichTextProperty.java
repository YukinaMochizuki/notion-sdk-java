package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RichTextProperty extends PageProperty {

    private static final String RICH_TEXT_FIELD = "rich_text";

    @JsonProperty(RICH_TEXT_FIELD)
    private List<RichText> texts;

    @NotNull
    public static RichTextProperty of(List<RichText> texts) {
        RichTextProperty richTextProperty = new RichTextProperty();
        richTextProperty.setType(PropertyType.RICH_TEXT);
        richTextProperty.setTexts(texts);
        return richTextProperty;
    }
}
