package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

import java.net.URL;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class UrlProperty extends PageProperty {

    private static final String URL_FIELD = "url";

    @JsonProperty(URL_FIELD)
    private URL url;

    @NotNull
    public static UrlProperty of(URL url){
        UrlProperty urlProperty = new UrlProperty();
        urlProperty.setType(PropertyType.URL);
        urlProperty.setUrl(url);
        return urlProperty;
    }
}