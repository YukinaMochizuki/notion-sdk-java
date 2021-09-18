package tw.yukina.notion.sdk.model.block.file;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.common.file.FileObject;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PDFBlock extends Block {
    private static final String PDF_FIELD = "pdf";

    @JsonProperty(PDF_FIELD)
    private FileObject fileObject;
}
