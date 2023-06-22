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
public class PeoplePropertyFilter extends DatabasePropertyFilter {

    public static final String PEOPLE_FIELD = "people";

    @JsonProperty(PEOPLE_FIELD)
    private RelationFilterObject relationFilterObject;

    @NotNull
    public static PeoplePropertyFilter of(String property, RelationFilterObject relationFilterObject) {
        PeoplePropertyFilter peoplePropertyFilter = new PeoplePropertyFilter();
        peoplePropertyFilter.setName(property);
        peoplePropertyFilter.setRelationFilterObject(relationFilterObject);
        return peoplePropertyFilter;
    }

    public static PeoplePropertyFilter containsUuidOf(String property, String containsUuid) {
        return of(property, RelationFilterObject.containsUuidOf(containsUuid));
    }

    public static PeoplePropertyFilter doesNotContainsUuidOf(String property, String doesNotContainsUuid) {
        return of(property, RelationFilterObject.doesNotContainsUuidOf(doesNotContainsUuid));
    }

    public static PeoplePropertyFilter isEmptyOf(String property, boolean isEmpty) {
        return of(property, RelationFilterObject.isEmptyOf(isEmpty));
    }

    public static PeoplePropertyFilter isNotEmptyOf(String property, boolean isNotEmpty) {
        return of(property, RelationFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
