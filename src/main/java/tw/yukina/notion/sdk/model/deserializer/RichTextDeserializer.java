package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.Text;
import tw.yukina.notion.sdk.model.common.rich.TextType;
import tw.yukina.notion.sdk.model.parent.PageParent;
import tw.yukina.notion.sdk.model.parent.Parent;
import tw.yukina.notion.sdk.model.parent.WorkspaceParent;

import java.io.IOException;
import java.util.Objects;

public class RichTextDeserializer extends JsonDeserializer<RichText> {
    @Override
    public RichText deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        if(Objects.equals(type, TextType.TEXT.getField())){
            ObjectCodec codec = jsonParser.getCodec();
            return codec.treeToValue(node, Text.class);
        }
//        else if(Objects.equals(type, "workspace")){
//            ObjectCodec codec = jsonParser.getCodec();
//            return codec.treeToValue(node, WorkspaceParent.class);
//        }

        throw JsonMappingException.from(jsonParser, "Error");
    }
}
