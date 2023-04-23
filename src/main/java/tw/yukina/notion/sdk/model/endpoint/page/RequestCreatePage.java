package tw.yukina.notion.sdk.model.endpoint.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.parent.Parent;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestCreatePage {

    private static final String PARENT_FIELD = "parent";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String CHILDREN_FIELD = "children";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";

    @JsonProperty(PARENT_FIELD)
    private Parent parent;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, PageProperty> properties;

    @JsonProperty(CHILDREN_FIELD)
    private List<BlockModel> children = new ArrayList<>();

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @JsonProperty(COVER_FIELD)
    private FileObject fileObject;

}
