package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class TemplateBlockModel extends BlockModel {
    private static final String TEMPLATE_FIELD = "template";

    @JsonProperty(TEMPLATE_FIELD)
    private Template template;

    @NotNull
    public static TemplateBlockModel of(String plainText, List<BlockModel> blocks) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts, blocks);
    }

    @NotNull
    public static TemplateBlockModel of(List<RichText> title, List<BlockModel> blocks) {
        Template template = new Template(title, blocks);
        TemplateBlockModel templateBlock = new TemplateBlockModel(template);
        templateBlock.setType(BlockType.TEMPLATE);
        return templateBlock;
    }

    @NotNull
    public static TemplateBlockModel of(List<RichText> title) {
        Template template = new Template();
        template.setTitle(title);
        TemplateBlockModel templateBlock = new TemplateBlockModel(template);
        templateBlock.setType(BlockType.TEMPLATE);
        return templateBlock;
    }

    @Override
    public boolean canHaveChildren() {
        return true;
    }
}
