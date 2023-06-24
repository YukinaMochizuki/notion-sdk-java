package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.database.property.*;

import java.io.IOException;

public class DatabasePropertyDeserializer extends AbstractDeserializer<DatabaseProperty> {

    @Override
    public DatabaseProperty deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        if (type.matches("title|rich_text|date|people|files|checkbox|url|email|phone_number|" +
                "created_time|created_by|last_edited_time|last_edited_by")) {
            return deserialize(jsonParser, node);
        }

        addAvailableType("number", NumberProperty.class);
        addAvailableType("select", SelectProperty.class);
        addAvailableType("multi_select", MultiSelectProperty.class);
        addAvailableType("formula", FormulaProperty.class);
        addAvailableType("relation", RelationProperty.class);
        addAvailableType("rollup", RollupProperty.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }

    private DatabaseProperty deserialize(JsonParser jsonParser, JsonNode node) throws JsonProcessingException {

        DatabaseProperty property = new DatabaseProperty();
        property.setType(jsonParser.getCodec().treeToValue(node.get("type"), PropertyType.class));
        property.setId(node.get("id").asText());
        property.setName(node.get("name").asText());

        return property;
    }
}
