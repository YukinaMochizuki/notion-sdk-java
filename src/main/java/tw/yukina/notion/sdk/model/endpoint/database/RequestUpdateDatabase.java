package tw.yukina.notion.sdk.model.endpoint.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;
import tw.yukina.notion.sdk.model.page.property.PageProperty;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestUpdateDatabase {

    private static final String TITLE_FIELD = "title";
    private static final String PROPERTIES_FIELD = "properties";

    @JsonProperty(TITLE_FIELD)
    private List<RichText> title;

    @JsonProperty(PROPERTIES_FIELD)
    private Map<String, DatabaseProperty> properties;

    public static RequestUpdateDatabase of(Database database){
        RequestUpdateDatabase requestUpdateDatabase = new RequestUpdateDatabase();
        requestUpdateDatabase.setTitle(database.getTitle());
        requestUpdateDatabase.setProperties(database.getPropertyMap());
        return requestUpdateDatabase;
    }
}
