package tw.yukina.notion.sdk.model.block.heading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class HeadingTwoBlockModel extends BlockModel implements HeadingBlock {
    private static final String HEADING_ONE_FIELD = "heading_2";

    @JsonProperty(HEADING_ONE_FIELD)
    private Heading heading;

    @NotNull
    public static HeadingTwoBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static HeadingTwoBlockModel of(List<RichText> richTexts) {
        Heading heading = new Heading();
        heading.setRichTexts(richTexts);
        heading.setColor(TextColor.DEFAULT);
        heading.setToggleable(false);
        HeadingTwoBlockModel headingTwoBlockModel = new HeadingTwoBlockModel();
        headingTwoBlockModel.setHeading(heading);
        headingTwoBlockModel.setType(BlockType.HEADING_2);
        return headingTwoBlockModel;
    }

    @Override
    public boolean canHaveChildren() {
        return false;
    }
}
