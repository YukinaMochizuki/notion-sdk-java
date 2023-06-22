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

    public static LastEditedByPropertyFilter of(String property, RelationFilterObject relationFilterObject) {
        LastEditedByPropertyFilter lastEditedByPropertyFilter = new LastEditedByPropertyFilter();
        lastEditedByPropertyFilter.setName(property);
        lastEditedByPropertyFilter.setRelationFilterObject(relationFilterObject);
        return lastEditedByPropertyFilter;
    }

    public static LastEditedByPropertyFilter containsUuidOf(String property, String containsUuid) {
        return of(property, RelationFilterObject.containsUuidOf(containsUuid));
    }

    public static LastEditedByPropertyFilter doesNotContainsUuidOf(String property, String doesNotContainsUuid) {
        return of(property, RelationFilterObject.doesNotContainsUuidOf(doesNotContainsUuid));
    }

    public static LastEditedByPropertyFilter isEmptyOf(String property, boolean isEmpty) {
        return of(property, RelationFilterObject.isEmptyOf(isEmpty));
    }

    public static LastEditedByPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty) {
        return of(property, RelationFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
