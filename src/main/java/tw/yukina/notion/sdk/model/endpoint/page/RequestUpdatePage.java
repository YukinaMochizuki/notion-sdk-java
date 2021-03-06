package tw.yukina.notion.sdk.model.endpoint.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.yukina.notion.sdk.model.common.Icon;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.page.Page;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestUpdatePage {

    private static final String PROPERTIES_FIELD = "properties";
    private static final String ARCHIVED_FIELD = "archived";
    private static final String ICON_FIELD = "icon";
    private static final String COVER_FIELD = "cover";

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, PageProperty> properties;

    @JsonProperty(ARCHIVED_FIELD)
    private boolean isArchived;

    @JsonProperty(ICON_FIELD)
    private Icon icon;

    @JsonProperty(COVER_FIELD)
    private FileObject fileObject;

    public static RequestUpdatePage of(Page page){
        RequestUpdatePage requestUpdatePage = new RequestUpdatePage();
        requestUpdatePage.setProperties(page.getPropertyMap());
        requestUpdatePage.setArchived(page.isArchived());
        requestUpdatePage.setIcon(page.getIcon());
        requestUpdatePage.setFileObject(page.getCover());
        return requestUpdatePage;
    }

}
