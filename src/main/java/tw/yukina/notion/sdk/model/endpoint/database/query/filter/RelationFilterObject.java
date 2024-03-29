package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelationFilterObject {

    public static final String CONTAINS_FIELD = "contains";
    public static final String DOES_NOT_CONTAINS_FIELD = "does_not_contain";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(CONTAINS_FIELD)
    private String containsUuid;

    @JsonProperty(DOES_NOT_CONTAINS_FIELD)
    private String doesNotContainsUuid;

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static RelationFilterObject containsUuidOf(String containsUuid) {
        RelationFilterObject relationFilterObject = new RelationFilterObject();
        relationFilterObject.setContainsUuid(containsUuid);
        return relationFilterObject;
    }

    @NotNull
    public static RelationFilterObject doesNotContainsUuidOf(String doesNotContainsUuid) {
        RelationFilterObject relationFilterObject = new RelationFilterObject();
        relationFilterObject.setDoesNotContainsUuid(doesNotContainsUuid);
        return relationFilterObject;
    }

    @NotNull
    public static RelationFilterObject isEmptyOf(boolean isEmpty) {
        RelationFilterObject relationFilterObject = new RelationFilterObject();
        relationFilterObject.setIsEmpty(isEmpty);
        return relationFilterObject;
    }

    @NotNull
    public static RelationFilterObject isNotEmptyOf(boolean isNotEmpty) {
        RelationFilterObject relationFilterObject = new RelationFilterObject();
        relationFilterObject.setIsNotEmpty(isNotEmpty);
        return relationFilterObject;
    }
}
