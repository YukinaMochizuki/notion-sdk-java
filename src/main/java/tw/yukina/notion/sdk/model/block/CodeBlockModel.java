package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.builder.TextBuilder;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class CodeBlockModel extends BlockModel {
    private static final String CODE_FIELD = "code";

    @JsonProperty(CODE_FIELD)
    private Code code;

    @NotNull
    public static CodeBlockModel of(String plainText, CodeLanguageType languageType, String caption) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        List<RichText> captionRichTexts = TextBuilder.of(caption).build();
        return of(richTexts, languageType, captionRichTexts);
    }

    @NotNull
    public static CodeBlockModel of(String plainText, CodeLanguageType languageType) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts, languageType);
    }

    @NotNull
    public static CodeBlockModel of(List<RichText> richTexts, CodeLanguageType languageType, List<RichText> caption) {
        Code code = new Code(richTexts, languageType, caption);
        CodeBlockModel codeBlock = new CodeBlockModel(code);
        codeBlock.setType(BlockType.CODE);
        return codeBlock;
    }

    @NotNull
    public static CodeBlockModel of(List<RichText> richTexts, CodeLanguageType languageType) {
        Code code = new Code(richTexts, languageType, Collections.emptyList());
        CodeBlockModel codeBlock = new CodeBlockModel(code);
        codeBlock.setType(BlockType.CODE);
        return codeBlock;
    }

    @Override
    public boolean canHaveChildren() {
        return false;
    }
}
