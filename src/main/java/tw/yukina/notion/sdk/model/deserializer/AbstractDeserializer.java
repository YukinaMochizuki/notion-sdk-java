package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {

    private final List<TypeUnit<T>> typeUnits = new ArrayList<>();

    public Optional<T> typeDeserialize(String type, JsonNode node, ObjectCodec codec) throws JsonProcessingException {
        for(TypeUnit typeUnit: typeUnits){
            if(Objects.equals(typeUnit.getType(), type)) return Optional.of((T)codec.treeToValue(node, typeUnit.getClazz()));
        }
        return Optional.empty();
    }

    public void addAvailableType(String type, Class<? extends T> clazz){
        typeUnits.add(new TypeUnit<>(type, clazz));
    }

    public JsonMappingException throwTypeNotFound(String type, JsonParser jsonParser) {
        return JsonMappingException.from(jsonParser, "The type \"" + type + "\" does not match any available types");
    }
}
