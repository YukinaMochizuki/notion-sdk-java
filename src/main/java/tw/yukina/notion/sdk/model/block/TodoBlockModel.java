package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TodoBlockModel extends BlockModel implements TextBlock {
    private static final String TO_DO_FIELD = "to_do";

    @JsonProperty(TO_DO_FIELD)
    private Todo todo;

    @NotNull
    public static TodoBlockModel of(String plainText) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts);
    }

    @NotNull
    public static TodoBlockModel of(String plainText, boolean check) {
        List<RichText> richTexts = TextBuilder.of(plainText).build();
        return of(richTexts, check);
    }

    @NotNull
    public static TodoBlockModel of(List<RichText> richTexts) {
        Todo todo = new Todo();
        todo.setRichTexts(richTexts);
        todo.setChecked(false);
        TodoBlockModel todoBlock = new TodoBlockModel();
        todoBlock.setTodo(todo);
        todoBlock.setType(BlockType.TO_DO);
        return todoBlock;
    }

    @NotNull
    public static TodoBlockModel of(List<RichText> richTexts, boolean check) {
        Todo todo = new Todo();
        todo.setRichTexts(richTexts);
        todo.setChecked(check);
        TodoBlockModel todoBlock = new TodoBlockModel();
        todoBlock.setTodo(todo);
        todoBlock.setType(BlockType.TO_DO);
        return todoBlock;
    }

    @Override
    @JsonIgnore
    public Paragraph getParagraph() {
        return todo;
    }

    @Override
    public void setParagraph(Paragraph paragraph) {
        this.todo.setRichTexts(paragraph.getRichTexts());
        this.todo.setChildren(paragraph.getChildren());
    }

    @Override
    public boolean canHaveChildren() {
        return true;
    }
}
