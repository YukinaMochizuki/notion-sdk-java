package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockType;
import tw.yukina.notion.sdk.model.block.ParagraphBlock;
import tw.yukina.notion.sdk.model.parent.PageParent;
import tw.yukina.notion.sdk.model.parent.Parent;
import tw.yukina.notion.sdk.model.parent.WorkspaceParent;

import java.io.IOException;
import java.util.Objects;

public class BlockDeserializer extends JsonDeserializer<Block> {
    @Override
    public Block deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        if(Objects.equals(type, BlockType.PARAGRAPH.getField())){
            ObjectCodec codec = jsonParser.getCodec();
            return codec.treeToValue(node, ParagraphBlock.class);
        }
//        else if(Objects.equals(type, "workspace")){
//            ObjectCodec codec = jsonParser.getCodec();
//            return codec.treeToValue(node, WorkspaceParent.class);
//        }

        throw JsonMappingException.from(jsonParser, "Error");
    }
}
