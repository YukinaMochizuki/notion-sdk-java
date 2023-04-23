package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.IconEmoji;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;


@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class CalloutBlockModel extends BlockModel implements TextBlock {
    private static final String CALLOUT_FIELD = "callout";

    @JsonProperty(CALLOUT_FIELD)
    private Callout callout;

    @Override
    public void setParagraph(@NotNull Paragraph paragraph) {
        callout.setChildren(paragraph.getChildren());
        callout.setRichTexts(paragraph.getRichTexts());
    }

    @Override
    @JsonIgnore
    public Paragraph getParagraph() {
        return callout;
    }

    @NotNull
    public static CalloutBlockModel of(List<RichText> richTexts){
        Callout callout = new Callout();
        callout.setRichTexts(richTexts);
        CalloutBlockModel calloutBlock = new CalloutBlockModel();
        calloutBlock.setCallout(callout);
        calloutBlock.setType(BlockType.CALLOUT);
        return calloutBlock;
    }

    @NotNull
    public static CalloutBlockModel of(List<RichText> richTexts, Icon icon){
        Callout callout = new Callout();
        callout.setRichTexts(richTexts);
        callout.setIcon(icon);
        CalloutBlockModel calloutBlock = new CalloutBlockModel();
        calloutBlock.setCallout(callout);
        calloutBlock.setType(BlockType.CALLOUT);
        return calloutBlock;
    }

    @NotNull
    public static CalloutBlockModel of(List<RichText> richTexts, String emoji){
        IconEmoji iconEmoji = IconEmoji.of(emoji);
        Callout callout = new Callout();
        callout.setRichTexts(richTexts);
        callout.setIcon(iconEmoji);
        CalloutBlockModel calloutBlock = new CalloutBlockModel();
        calloutBlock.setCallout(callout);
        calloutBlock.setType(BlockType.CALLOUT);
        return calloutBlock;
    }
}
