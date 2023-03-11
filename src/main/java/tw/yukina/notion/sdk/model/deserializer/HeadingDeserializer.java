package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.heading.Heading;
import tw.yukina.notion.sdk.model.block.heading.NotToggleHeading;
import tw.yukina.notion.sdk.model.block.heading.ToggleHeading;
import tw.yukina.notion.sdk.model.page.property.*;

import java.io.IOException;

public class HeadingDeserializer extends AbstractDeserializer<Heading> {
    @Override
    public Heading deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        boolean isToggleable = node.get("is_toggleable").asBoolean();

        if(isToggleable) return jsonParser.getCodec().treeToValue(node, ToggleHeading.class);
        else return jsonParser.getCodec().treeToValue(node, NotToggleHeading.class);
    }
}
