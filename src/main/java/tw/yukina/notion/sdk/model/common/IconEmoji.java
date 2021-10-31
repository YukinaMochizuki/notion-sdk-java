package tw.yukina.notion.sdk.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class IconEmoji extends Icon {

    private static final String EMOJI_FIELD = "emoji";

    @JsonProperty(EMOJI_FIELD)
    private String emoji;

    @NotNull
    public static IconEmoji of(String emoji){
        IconEmoji iconEmoji = new IconEmoji();
        iconEmoji.setType("emoji");
        iconEmoji.setEmoji(emoji);
        return iconEmoji;
    }
}
