package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.page.property.rollup.*;

import java.io.IOException;

public class RollupDeserializer extends AbstractDeserializer<RollupObject> {
    @Override
    public RollupObject deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(RollupType.DATE.getField(), DateRollupProperty.class);
        addAvailableType(RollupType.NUMBER.getField(), NumberRollupProperty.class);
        addAvailableType(RollupType.ARRAY.getField(), ArrayRollupProperty.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));

    }
}
