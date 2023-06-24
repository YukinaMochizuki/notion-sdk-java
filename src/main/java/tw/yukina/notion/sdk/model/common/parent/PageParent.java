package tw.yukina.notion.sdk.model.common.parent;

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
public class PageParent extends Parent {

    private static final String PAGE_ID_FIELD = "page_id";

    @JsonProperty(PAGE_ID_FIELD)
    private String pageId;

    @NotNull
    public static PageParent of(String pageId) {
        PageParent pageParent = new PageParent();
        pageParent.setParentType(ParentType.PAGE);
        pageParent.setPageId(pageId);
        return pageParent;
    }
}
