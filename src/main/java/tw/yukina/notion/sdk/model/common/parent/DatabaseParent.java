package tw.yukina.notion.sdk.model.common.parent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DatabaseParent extends Parent {

    private static final String DATABASE_ID_FIELD = "database_id";

    @JsonProperty(DATABASE_ID_FIELD)
    private String databaseId;

    @NotNull
    public static DatabaseParent of(String databaseId) {
        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setParentType(ParentType.DATABASE);
        databaseParent.setDatabaseId(databaseId);
        return databaseParent;
    }
}
