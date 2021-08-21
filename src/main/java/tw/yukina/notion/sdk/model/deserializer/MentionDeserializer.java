package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;
import tw.yukina.notion.sdk.model.common.rich.mention.*;

import java.io.IOException;
import java.util.Objects;

public class MentionDeserializer extends AbstractDeserializer<Mention> {
    @Override
    public Mention deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(MentionType.USER.getField(), UserMention.class);
        addAvailableType(MentionType.PAGE.getField(), PageMention.class);
        addAvailableType(MentionType.DATABASE.getField(), DatabaseMention.class);
        addAvailableType(MentionType.DATE.getField(), DateMention.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
