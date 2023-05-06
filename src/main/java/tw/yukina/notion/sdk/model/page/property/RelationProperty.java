package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.common.PropertyType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class RelationProperty extends PageProperty {

    private static final String RELATION_FIELD = "relation";

    private static final String HAS_MORE_FIELD = "has_more";

    @JsonProperty(RELATION_FIELD)
    private List<RelationObject> relation;

    @JsonProperty(HAS_MORE_FIELD)
    private boolean hasMore;

    public static RelationProperty of(String ...id){
        List<RelationObject> relationObjects = new ArrayList<>();
        for(String s: id){
            RelationObject relationObject = new RelationObject();
            relationObject.setId(s);
            relationObjects.add(relationObject);
        }
        RelationProperty relationProperty = new RelationProperty();
        relationProperty.setType(PropertyType.RELATION);
        relationProperty.setRelation(relationObjects);
        return relationProperty;
    }
}
