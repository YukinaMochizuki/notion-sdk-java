package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.RichTextDeserializer;

import java.net.URL;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = RichTextDeserializer.class)
public class RichText {
    private static final String PLAIN_TEXT_FIELD = "plain_text";
    private static final String ANNOTATIONS_FIELD = "annotations";
    private static final String TYPE_FIELD = "type";
    private static final String HREF_FIELD = "href";

    @JsonProperty(PLAIN_TEXT_FIELD)
    private String plainText;

    @JsonProperty(ANNOTATIONS_FIELD)
    private Annotation annotations;

    @JsonProperty(TYPE_FIELD)
    private TextType type;

    @JsonProperty(HREF_FIELD)
    private URL href;
}
