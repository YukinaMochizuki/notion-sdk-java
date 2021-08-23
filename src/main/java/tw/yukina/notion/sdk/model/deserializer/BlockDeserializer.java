package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingThreeBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingTwoBlock;
import tw.yukina.notion.sdk.model.block.list.BulletedListBlock;
import tw.yukina.notion.sdk.model.block.list.NumberedListBlock;
import tw.yukina.notion.sdk.model.block.list.ToggleBlock;

import java.io.IOException;

public class BlockDeserializer extends AbstractDeserializer<Block> {
    @Override
    public Block deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(BlockType.PARAGRAPH.getField(), ParagraphBlock.class);

        addAvailableType(BlockType.HEADING_1.getField(), HeadingOneBlock.class);
        addAvailableType(BlockType.HEADING_2.getField(), HeadingTwoBlock.class);
        addAvailableType(BlockType.HEADING_3.getField(), HeadingThreeBlock.class);

        addAvailableType(BlockType.BULLETED_LIST_ITEM.getField(), BulletedListBlock.class);
        addAvailableType(BlockType.NUMBERED_LIST_ITEM.getField(), NumberedListBlock.class);
        addAvailableType(BlockType.TOGGLE.getField(), ToggleBlock.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
