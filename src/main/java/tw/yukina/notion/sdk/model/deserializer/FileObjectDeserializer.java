package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.file.ExternalSourceFile;
import tw.yukina.notion.sdk.model.common.file.FileObject;
import tw.yukina.notion.sdk.model.common.file.FileType;
import tw.yukina.notion.sdk.model.common.file.NotionSourceFile;

import java.io.IOException;

public class FileObjectDeserializer extends AbstractDeserializer<FileObject> {
    @Override
    public FileObject deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(FileType.FILE.getField(), NotionSourceFile.class);
        addAvailableType(FileType.EXTERNAL.getField(), ExternalSourceFile.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }
}
