package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.*;
import tw.yukina.notion.sdk.model.block.file.FileBlock;
import tw.yukina.notion.sdk.model.block.file.ImageBlock;
import tw.yukina.notion.sdk.model.block.file.PDFBlock;
import tw.yukina.notion.sdk.model.block.file.VideoBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingThreeBlock;
import tw.yukina.notion.sdk.model.block.heading.HeadingTwoBlock;
import tw.yukina.notion.sdk.model.block.list.BulletedListBlock;
import tw.yukina.notion.sdk.model.block.list.NumberedListBlock;
import tw.yukina.notion.sdk.model.block.list.ToggleBlock;
import tw.yukina.notion.sdk.model.page.property.*;

import java.io.IOException;

public class FormulaDeserializer extends AbstractDeserializer<FormulaObject> {
    @Override
    public FormulaObject deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType("boolean", FormulaBooleanObject.class);
        addAvailableType("date", FormulaDateObject.class);
        addAvailableType("number", FormulaNumberObject.class);
        addAvailableType("string", FormulaStringObject.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
