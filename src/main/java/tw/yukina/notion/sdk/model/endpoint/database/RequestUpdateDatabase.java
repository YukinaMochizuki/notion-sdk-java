package tw.yukina.notion.sdk.model.endpoint.database;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;

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

    public static RequestUpdateDatabase of(DatabaseModel databaseModel){
        RequestUpdateDatabase requestUpdateDatabase = new RequestUpdateDatabase();
        requestUpdateDatabase.setTitle(databaseModel.getTitle());
        requestUpdateDatabase.setProperties(databaseModel.getPropertyMap());
        return requestUpdateDatabase;
    }
}
