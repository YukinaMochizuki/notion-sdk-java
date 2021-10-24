package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import tw.yukina.notion.sdk.model.serializer.DatabasePropertyFilterSerializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = DatabasePropertyFilterSerializer.class)
public class DatabasePropertyFilter {

    public static final String PROPERTY_FIELD = "property";

    @JsonProperty(PROPERTY_FIELD)
    private String property;
}
