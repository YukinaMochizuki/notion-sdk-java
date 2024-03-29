package tw.yukina.notion.sdk.model.block.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.common.file.FileObject;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class FileBlockModel extends BlockModel {

    private static final String FILE_FIELD = "file";

    @JsonProperty(FILE_FIELD)
    private FileObject fileObject;

    @Override
    public boolean canHaveChildren() {
        return false;
    }
}
