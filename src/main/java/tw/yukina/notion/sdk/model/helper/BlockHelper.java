package tw.yukina.notion.sdk.model.helper;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;

import java.util.Collections;
import java.util.List;

public final class BlockHelper {

    private BlockHelper() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

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
        todo.setRichTexts(texts);
        todo.setChecked(checked);

        TodoBlock todoBlock = new TodoBlock();
        todoBlock.setHasChildren(false);
        todoBlock.setType(BlockType.TO_DO);
        todoBlock.setTodo(todo);

        return todoBlock;
    }

    @NotNull
    public static ChildPageBlock createDefaultChildPageBlock(String title){
        ChildPage childPage = new ChildPage();
        childPage.setTitle(title);

        ChildPageBlock childPageBlock = new ChildPageBlock();
        childPageBlock.setType(BlockType.CHILD_PAGE);
        childPageBlock.setChildPage(childPage);

        return childPageBlock;
    }
}
