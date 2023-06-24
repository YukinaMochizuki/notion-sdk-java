package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class ChildPageBlockModel extends BlockModel {
    private static final String CHILD_PAGE_FIELD = "child_page";

    @JsonProperty(CHILD_PAGE_FIELD)
    private ChildPage childPage;

    @NotNull
    public static ChildPageBlockModel of(String title) {
        ChildPage childPage = new ChildPage();
        childPage.setTitle(title);
        ChildPageBlockModel childPageBlock = new ChildPageBlockModel();
        childPageBlock.setChildPage(childPage);
        childPageBlock.setType(BlockType.CHILD_PAGE);
        return childPageBlock;
    }

    @Override
    public boolean canHaveChildren() {
        return false;
    }
}
