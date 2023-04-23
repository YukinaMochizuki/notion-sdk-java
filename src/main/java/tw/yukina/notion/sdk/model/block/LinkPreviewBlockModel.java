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
public class LinkPreviewBlockModel extends BlockModel {

    private static final String LINK_PREVIEW_FIELD = "link_preview";

    @JsonProperty(LINK_PREVIEW_FIELD)
    private LinkPreview linkPreview;

    @NotNull
    public static LinkPreviewBlockModel of(String url){
        LinkPreview linkPreview = new LinkPreview(url);
        LinkPreviewBlockModel linkPreviewBlock = new LinkPreviewBlockModel(linkPreview);
        linkPreviewBlock.setType(BlockType.LINK_PREVIEW);
        return linkPreviewBlock;
    }
}
