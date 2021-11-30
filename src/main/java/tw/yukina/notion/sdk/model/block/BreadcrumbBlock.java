package tw.yukina.notion.sdk.model.block;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.EmptyObject;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class BreadcrumbBlock extends Block {

    private static final String BREADCRUMB_FIELD = "breadcrumb";

    @JsonProperty(BREADCRUMB_FIELD)
    private EmptyObject emptyObject;

    @NotNull
    public static BreadcrumbBlock of(){
        BreadcrumbBlock breadcrumbBlock = new BreadcrumbBlock(EmptyObject.of());
        breadcrumbBlock.setType(BlockType.BREADCRUMB);
        return breadcrumbBlock;
    }
}
