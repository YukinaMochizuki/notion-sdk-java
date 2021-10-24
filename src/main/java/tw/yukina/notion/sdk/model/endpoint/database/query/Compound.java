package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.DatabasePropertyFilter;
import tw.yukina.notion.sdk.model.serializer.CompoundSerializer;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = CompoundSerializer.class)
public class Compound {

    private CompoundType compoundType;

    private Set<Compound> compounds = new HashSet<>();

    private Set<DatabasePropertyFilter> databasePropertyFilters = new HashSet<>();

}
