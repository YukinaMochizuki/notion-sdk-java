package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.User;
import tw.yukina.notion.sdk.model.common.user.UserType;

import java.io.IOException;

public class UserDeserializer extends AbstractDeserializer<User> {
    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(UserType.PERSON.getField(), PersonUser.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() -> throwTypeNotFound(type, jsonParser));
    }
}
