package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class CreatedByPropertyFilter extends DatabasePropertyFilter {

    public static final String CREATED_BY_FIELD = "created_by";

    @JsonProperty(CREATED_BY_FIELD)
    private RelationFilterObject relationFilterObject;

    @NotNull
    public static CreatedByPropertyFilter of(String property, RelationFilterObject relationFilterObject){
        CreatedByPropertyFilter createdByPropertyFilter = new CreatedByPropertyFilter();
        createdByPropertyFilter.setName(property);
        createdByPropertyFilter.setRelationFilterObject(relationFilterObject);
        return createdByPropertyFilter;
    }
}
