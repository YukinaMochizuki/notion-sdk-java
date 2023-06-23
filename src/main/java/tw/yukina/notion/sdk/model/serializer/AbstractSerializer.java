package tw.yukina.notion.sdk.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import tw.yukina.notion.sdk.model.deserializer.TypeUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSerializer<T> extends JsonSerializer<T> {

    private final List<TypeUnit<T>> typeUnits = new ArrayList<>();

    public void typeSerialize(T value, JsonGenerator gen) throws IOException {
        boolean typeAvailableFlag = false;

        for (TypeUnit<T> typeUnit : typeUnits) {
            if (checkTypeEquals(typeUnit, value)) {
                gen.writeObject(value);
                typeAvailableFlag = true;
            }
        }

        if (!typeAvailableFlag) throw throwTypeNotFound(value.getClass().getSimpleName(), gen);
    }

    public void typeSerializeRaw(T value, JsonGenerator gen, ObjectMapper mapper) throws IOException {
        boolean typeAvailableFlag = false;

        for (TypeUnit<T> typeUnit : typeUnits) {
            if (checkTypeEquals(typeUnit, value)) {
                gen.writeRawValue(mapper.writeValueAsString(value));
                typeAvailableFlag = true;
            }
        }

        if (!typeAvailableFlag) throw throwTypeNotFound(value.getClass().getSimpleName(), gen);
    }

    protected boolean checkTypeEquals(TypeUnit<T> typeUnit, T value) {
        return false;
    }

    protected void addAvailableType(String type, Class<? extends T> clazz) {
        typeUnits.add(new TypeUnit<>(type, clazz));
    }

    protected JsonMappingException throwTypeNotFound(String type, JsonGenerator jsonGenerator) {
        return JsonMappingException.from(jsonGenerator, "The type \"" + type + "\" does not match any available types");
    }
}
