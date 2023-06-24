package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class TitleProperty extends PageProperty {

    private static final String TITLE_FIELD = "title";

    @JsonProperty(TITLE_FIELD)
    private List<RichText> texts;

    @NotNull
    public static TitleProperty of(String text) {
        TitleProperty titleProperty = new TitleProperty();
        titleProperty.setType(PropertyType.TITLE);
        titleProperty.setTexts(RichTextHelper.createDefaultArrayText(text));
        return titleProperty;
    }
}
