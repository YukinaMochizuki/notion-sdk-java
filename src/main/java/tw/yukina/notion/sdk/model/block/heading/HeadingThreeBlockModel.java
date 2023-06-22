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
public class HeadingThreeBlockModel extends BlockModel implements HeadingBlock {
    private static final String HEADING_ONE_FIELD = "heading_3";

    @JsonProperty(HEADING_ONE_FIELD)
    private Heading heading;

    @Override
    public boolean canHaveChildren() {
        return false;
    }

    @NotNull
    public static HeadingThreeBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static HeadingThreeBlockModel of(List<RichText> richTexts) {
        Heading heading = new Heading();
        heading.setRichTexts(richTexts);
        heading.setColor(TextColor.DEFAULT);
        heading.setToggleable(false);
        HeadingThreeBlockModel headingThreeBlockModel = new HeadingThreeBlockModel();
        headingThreeBlockModel.setHeading(heading);
        headingThreeBlockModel.setType(BlockType.HEADING_3);
        return headingThreeBlockModel;
    }
}
