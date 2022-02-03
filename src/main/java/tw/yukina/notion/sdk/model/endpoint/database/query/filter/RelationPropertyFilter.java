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
public class RelationPropertyFilter extends DatabasePropertyFilter {

    public static final String RELATION_FIELD = "relation";

    @JsonProperty(RELATION_FIELD)
    private RelationFilterObject relationFilterObject;

    @NotNull
    public static RelationPropertyFilter of(String property, RelationFilterObject relationFilterObject){
        RelationPropertyFilter relationPropertyFilter = new RelationPropertyFilter();
        relationPropertyFilter.setName(property);
        relationPropertyFilter.setRelationFilterObject(relationFilterObject);
        return relationPropertyFilter;
    }
}
