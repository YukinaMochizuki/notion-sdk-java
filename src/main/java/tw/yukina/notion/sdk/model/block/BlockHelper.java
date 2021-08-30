package tw.yukina.notion.sdk.model.block;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.heading.Heading;
import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlock;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;

import java.util.Collections;
import java.util.List;

public class BlockHelper {

    @NotNull
    public static ParagraphBlock createDefaultParagraph(String content){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(Collections.singletonList(RichTextHelper.createDefaultText(content)));

        return getDefaultParagraphBlock(paragraph);
    }
    @NotNull
    public static ParagraphBlock createDefaultParagraph(RichText richText){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(Collections.singletonList(richText));

        return getDefaultParagraphBlock(paragraph);
    }

    @NotNull
    public static ParagraphBlock getDefaultParagraphBlock(Paragraph paragraph){
        ParagraphBlock paragraphBlock = new ParagraphBlock();
        paragraphBlock.setHasChildren(false);
        paragraphBlock.setType(BlockType.PARAGRAPH);
        paragraphBlock.setParagraph(paragraph);

        return paragraphBlock;
    }

    @NotNull
    public static TodoBlock createDefaultTodoBlock(List<RichText> texts, boolean checked){
        Todo todo = new Todo();
        todo.setTexts(texts);
        todo.setChecked(checked);

        TodoBlock todoBlock = new TodoBlock();
        todoBlock.setHasChildren(false);
        todoBlock.setType(BlockType.TO_DO);
        todoBlock.setTodo(todo);

        return todoBlock;
    }
}
