package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.NotionObject;
import tw.yukina.notion.sdk.model.ObjectType;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.page.Page;

import java.io.IOException;

public class NotionObjectDeserializer extends AbstractDeserializer<NotionObject> {
    @Override
    public NotionObject deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("object").asText();

        addAvailableType(ObjectType.DATABASE.getField(), Database.class);
        addAvailableType(ObjectType.PAGE.getField(), Page.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
