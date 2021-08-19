package tw.yukina.notion.sdk.model.parent;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class WorkspaceParent extends Parent {
    private Boolean workspace = true;
}
