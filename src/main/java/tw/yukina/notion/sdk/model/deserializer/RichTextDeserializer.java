package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.rich.*;

import java.io.IOException;

public class RichTextDeserializer extends AbstractDeserializer<RichText> {
    @Override
    public RichText deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(TextType.TEXT.getField(), Text.class);
        addAvailableType(TextType.MENTION.getField(), MentionText.class);
        addAvailableType(TextType.EQUATION.getField(), EquationText.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }
}
