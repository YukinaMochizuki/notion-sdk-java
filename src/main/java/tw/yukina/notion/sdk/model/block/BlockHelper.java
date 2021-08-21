package tw.yukina.notion.sdk.model.block;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.util.Collections;

public class BlockHelper {

    @NotNull
    public static ParagraphBlock createDefaultParagraph(String content){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(Collections.singletonList(RichTextHelper.createDefaultText(content)));

        ParagraphBlock paragraphBlock = new ParagraphBlock();
        paragraphBlock.setParagraph(paragraph);
        paragraphBlock.setHasChildren(false);
        paragraphBlock.setType(BlockType.PARAGRAPH);

        return paragraphBlock;
    }
}
