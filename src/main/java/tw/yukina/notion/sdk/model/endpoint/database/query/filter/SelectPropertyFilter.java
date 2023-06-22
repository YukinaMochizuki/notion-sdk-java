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
public class SelectPropertyFilter extends DatabasePropertyFilter {

    public static final String SELECT_FIELD = "select";

    @JsonProperty(SELECT_FIELD)
    private SelectFilterObject selectFilterObject;

    @NotNull
    public static SelectPropertyFilter of(String property, SelectFilterObject selectFilterObject){
        SelectPropertyFilter selectPropertyFilter = new SelectPropertyFilter();
        selectPropertyFilter.setName(property);
        selectPropertyFilter.setSelectFilterObject(selectFilterObject);
        return selectPropertyFilter;
    }

    public static SelectPropertyFilter equalsOf(String property, String equals){
        return of(property, SelectFilterObject.equalsOf(equals));
    }

    public static SelectPropertyFilter doesNotEqualOf(String property, String doesNotEqual){
        return of(property, SelectFilterObject.doesNotEqualOf(doesNotEqual));
    }

    public static SelectPropertyFilter isEmptyOf(String property, boolean isEmpty){
        return of(property, SelectFilterObject.isEmptyOf(isEmpty));
    }

    public static SelectPropertyFilter isNotEmptyOf(String property, boolean isNotEmpty){
        return of(property, SelectFilterObject.isNotEmptyOf(isNotEmpty));
    }
}
