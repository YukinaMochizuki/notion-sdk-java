package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;

public class BlockDeserializer extends AbstractDeserializer<Block> {
    @Override
    public Block deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(BlockType.PARAGRAPH.getField(), ParagraphBlock.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
