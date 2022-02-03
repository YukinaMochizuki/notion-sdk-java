package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = JsonSerializer.None.class)
public class FilesPropertyFilter extends DatabasePropertyFilter {

    public static final String FILES_FIELD = "files";

    @JsonProperty(FILES_FIELD)
    private FilesFilterObject filesFilterObject;

    @NotNull
    public static FilesPropertyFilter of(String property, FilesFilterObject filesFilterObject){
        FilesPropertyFilter filesPropertyFilter = new FilesPropertyFilter();
        filesPropertyFilter.setName(property);
        filesPropertyFilter.setFilesFilterObject(filesFilterObject);
        return filesPropertyFilter;
    }
}
