package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.parent.PageParent;
import tw.yukina.notion.sdk.model.parent.Parent;
import tw.yukina.notion.sdk.model.parent.ParentType;
import tw.yukina.notion.sdk.model.parent.WorkspaceParent;

import java.io.IOException;

public class ParentDeserializer extends AbstractDeserializer<Parent> {
    @Override
    public Parent deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(ParentType.PAGE.getField(), PageParent.class);
        addAvailableType(ParentType.WORKSPACE.getField(), WorkspaceParent.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
