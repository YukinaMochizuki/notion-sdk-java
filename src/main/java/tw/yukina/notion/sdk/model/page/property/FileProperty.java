package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.file.ExternalSourceFile;
import tw.yukina.notion.sdk.model.common.file.FileObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class FileProperty extends PageProperty {

    private static final String FILES_FIELD = "files";

    @JsonProperty(FILES_FIELD)
    private List<FileObject> fileObjects;

    @NotNull
    public static FileProperty of(@NotNull Map<String, URL> externalFile){
        List<FileObject> fileObjects = new ArrayList<>();
        externalFile.forEach(((s, url) -> {
            fileObjects.add(ExternalSourceFile.of(s, url));
        }));
        FileProperty fileProperty = new FileProperty();
        fileProperty.setType(PropertyType.FILES);
        fileProperty.setFileObjects(fileObjects);
        return fileProperty;
    }
}