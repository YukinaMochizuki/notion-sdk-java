package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;

import java.io.IOException;
import java.util.Objects;

public class MentionDeserializer extends JsonDeserializer<Mention> {
    @Override
    public Mention deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

//        if(Objects.equals(type, MentionType.USER.getField())){
//            ObjectCodec codec = jsonParser.getCodec();
//            return codec.treeToValue(node, ParagraphBlock.class);
//        }
//        else if(Objects.equals(type, "workspace")){
//            ObjectCodec codec = jsonParser.getCodec();
//            return codec.treeToValue(node, WorkspaceParent.class);
//        }

        throw JsonMappingException.from(jsonParser, "Error");
    }
}
