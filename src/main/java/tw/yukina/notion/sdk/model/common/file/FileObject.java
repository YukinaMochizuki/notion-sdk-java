package tw.yukina.notion.sdk.model.common.file;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.FileObjectDeserializer;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = FileObjectDeserializer.class)
public class FileObject {

    private static final String TYPE_FIELD = "type";
    private static final String NAME_FIELD = "name";
    private static final String EXTERNAL_FIELD = "external";

    @JsonProperty(TYPE_FIELD)
    private FileType fileType;

    @JsonProperty(NAME_FIELD)
    private String name;

}
