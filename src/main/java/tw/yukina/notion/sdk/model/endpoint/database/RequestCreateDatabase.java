package tw.yukina.notion.sdk.model.endpoint.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.yukina.notion.sdk.model.common.parent.Parent;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestCreateDatabase {

    private static final String PARENT_FIELD = "parent";
    private static final String PROPERTIES_FIELD = "properties";
    private static final String TITLE_FIELD = "title";

    @JsonProperty(PARENT_FIELD)
    private Parent parent;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, DatabaseProperty> properties;

    @JsonProperty(TITLE_FIELD)
    private List<RichText> title = new ArrayList<>();

}
