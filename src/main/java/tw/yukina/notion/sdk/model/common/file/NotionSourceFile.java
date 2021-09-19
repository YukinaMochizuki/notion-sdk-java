package tw.yukina.notion.sdk.model.common.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class NotionSourceFile extends FileObject {

    private static final String FILE_FIELD = "file";

    @JsonProperty(FILE_FIELD)
    private NotionSourceFileObject notionSourceFileObject;

}
