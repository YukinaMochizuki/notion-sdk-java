package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilesFilterObject {

    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @NotNull
    public static FilesFilterObject isEmptyOf(boolean isEmpty){
        FilesFilterObject filesFilterObject = new FilesFilterObject();
        filesFilterObject.setIsEmpty(isEmpty);
        return filesFilterObject;
    }

    @NotNull
    public static FilesFilterObject isNotEmptyOf(boolean isNotEmpty){
        FilesFilterObject filesFilterObject = new FilesFilterObject();
        filesFilterObject.setIsNotEmpty(isNotEmpty);
        return filesFilterObject;
    }
}
