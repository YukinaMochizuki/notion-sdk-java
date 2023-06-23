package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.database.property.DualPropertyRelation;
import tw.yukina.notion.sdk.model.database.property.RelationObject;
import tw.yukina.notion.sdk.model.database.property.RelationPropertyType;
import tw.yukina.notion.sdk.model.database.property.SinglePropertyRelation;

import java.io.IOException;

public class DatabaseRelationDeserializer extends AbstractDeserializer<RelationObject> {
    @Override
    public RelationObject deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(RelationPropertyType.SINGLE_PROPERTY.getField(), SinglePropertyRelation.class);
        addAvailableType(RelationPropertyType.DUAL_PROPERTY.getField(), DualPropertyRelation.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }
}
