package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tw.yukina.notion.sdk.model.common.unit.Database;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatabaseMention extends Mention {
    private static final String DATABASE_FIELD = "database";

    private Database database;
}