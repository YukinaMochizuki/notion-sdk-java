package tw.yukina.notion.sdk.model.helper;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.block.*;
import tw.yukina.notion.sdk.model.common.rich.RichText;

import java.util.Collections;
import java.util.List;

public final class BlockHelper {

    private BlockHelper() {
        throw new java.lang.UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @NotNull
    public static ParagraphBlockModel createDefaultParagraph(String content){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(Collections.singletonList(RichTextHelper.createDefaultText(content)));
        paragraph.setColor(TextColor.DEFAULT);

        return getDefaultParagraphBlock(paragraph);
    }

    @NotNull
    public static ParagraphBlockModel createDefaultParagraph(RichText richText){
        Paragraph paragraph = new Paragraph();
        paragraph.setRichTexts(Collections.singletonList(richText));
        paragraph.setColor(TextColor.DEFAULT);

        return getDefaultParagraphBlock(paragraph);
    }

    @NotNull
    public static ParagraphBlockModel getDefaultParagraphBlock(Paragraph paragraph){
        ParagraphBlockModel paragraphBlock = new ParagraphBlockModel();
        paragraphBlock.setHasChildren(false);
        paragraphBlock.setType(BlockType.PARAGRAPH);
        paragraphBlock.setParagraph(paragraph);

        return paragraphBlock;
    }

    @NotNull
    public static TodoBlockModel createDefaultTodoBlock(List<RichText> texts, boolean checked){
        Todo todo = new Todo();
        todo.setRichTexts(texts);
        todo.setChecked(checked);
        todo.setColor(TextColor.DEFAULT);

        TodoBlockModel todoBlock = new TodoBlockModel();
        todoBlock.setHasChildren(false);
        todoBlock.setType(BlockType.TO_DO);
        todoBlock.setTodo(todo);

        return todoBlock;
    }

    @NotNull
    public static ChildPageBlockModel createDefaultChildPageBlock(String title){
        ChildPage childPage = new ChildPage();
        childPage.setTitle(title);

        ChildPageBlockModel childPageBlock = new ChildPageBlockModel();
        childPageBlock.setType(BlockType.CHILD_PAGE);
        childPageBlock.setChildPage(childPage);

        return childPageBlock;
    }

    @NotNull
    public static ChildDatabaseBlockModel createDefaultDatabasePageBlock(String title){
        ChildPage childPage = new ChildPage();
        childPage.setTitle(title);

        ChildDatabaseBlockModel childDatabaseBlock = new ChildDatabaseBlockModel();
        childDatabaseBlock.setType(BlockType.CHILD_DATABASE);
        childDatabaseBlock.setChildPage(childPage);

        return childDatabaseBlock;
    }
}
