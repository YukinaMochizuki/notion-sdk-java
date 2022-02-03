package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class LastEditedByPropertyFilter extends DatabasePropertyFilter {

    public static final String CREATED_BY_FIELD = "last_edited_by";

    @JsonProperty(CREATED_BY_FIELD)
    private RelationFilterObject relationFilterObject;

    public static LastEditedByPropertyFilter of(String property, RelationFilterObject relationFilterObject){
        LastEditedByPropertyFilter lastEditedByPropertyFilter = new LastEditedByPropertyFilter();
        lastEditedByPropertyFilter.setName(property);
        lastEditedByPropertyFilter.setRelationFilterObject(relationFilterObject);
        return lastEditedByPropertyFilter;
    }
}
