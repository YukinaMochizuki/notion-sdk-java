package tw.yukina.notion.sdk.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import tw.yukina.notion.sdk.model.common.Property;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.database.property.*;
import tw.yukina.notion.sdk.model.deserializer.TypeUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabasePropertySerializer extends JsonSerializer<DatabaseProperty> {

    @Override
    public void serialize(DatabaseProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();

        if(value.getType().getField().matches("title|rich_text|date|people|files|checkbox|url|email|phone_number|created_time|created_by|last_edited_time|last_edited_by")){
            packageEmptyObjectType(value, gen);
            gen.writeEndObject();
            return;
        }

        addAvailableType(PropertyType.FORMULA.getField(), FormulaProperty.class);
        addAvailableType(PropertyType.MULTI_SELECT.getField(), MultiSelectProperty.class);
        addAvailableType(PropertyType.NUMBER.getField(), NumberProperty.class);
        addAvailableType(PropertyType.RELATION.getField(), RelationProperty.class);
        addAvailableType(PropertyType.ROLLUP.getField(), RollupProperty.class);
        addAvailableType(PropertyType.SELECT.getField(), SelectProperty.class);

        typeSerialize(value, gen, (ObjectMapper) gen.getCodec());

        gen.writeEndObject();
    }

    private void packageEmptyObjectType(DatabaseProperty value, JsonGenerator gen) throws IOException {
        gen.writeObjectFieldStart(value.getType().getField());
        gen.writeEndObject();
        gen.writeStringField(DatabaseProperty.NAME_FIELD, value.getName());
        gen.writeStringField(Property.ID_FIELD, value.getId());
        gen.writeStringField(Property.TYPE_FIELD, value.getType().getField());
    }

    private final List<TypeUnit<DatabaseProperty>> typeUnits = new ArrayList<>();

    @SuppressWarnings({"rawtypes"})
    public void typeSerialize(DatabaseProperty value, JsonGenerator gen, ObjectMapper mapper) throws IOException {
        for(TypeUnit typeUnit: typeUnits){
            if(Objects.equals(typeUnit.getType(), value.getType().getField())) {
                gen.writeRaw(mapper.writeValueAsString(value));
            };
        }
    }

    public void addAvailableType(String type, Class<? extends DatabaseProperty> clazz){
        typeUnits.add(new TypeUnit<>(type, clazz));
    }

    public JsonMappingException throwTypeNotFound(String type, JsonParser jsonParser) {
        return JsonMappingException.from(jsonParser, "The type \"" + type + "\" does not match any available types");
    }
}
