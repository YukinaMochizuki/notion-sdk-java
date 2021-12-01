package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class TemplateBlock extends Block {
    private static final String TEMPLATE_FIELD = "template";

    @JsonProperty(TEMPLATE_FIELD)
    private Template template;

    @NotNull
    public static TemplateBlock of(List<RichText> title, List<Block> blocks) {
        Template template = new Template(title, blocks);
        TemplateBlock templateBlock = new TemplateBlock(template);
        templateBlock.setType(BlockType.TEMPLATE);
        return templateBlock;
    }

    @NotNull
    public static TemplateBlock of(List<RichText> title) {
        Template template = new Template();
        template.setTitle(title);
        TemplateBlock templateBlock = new TemplateBlock(template);
        templateBlock.setType(BlockType.TEMPLATE);
        return templateBlock;
    }
}
