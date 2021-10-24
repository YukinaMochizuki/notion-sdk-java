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

public class DatabasePropertySerializer extends AbstractSerializer<DatabaseProperty> {

    @Override
    public void serialize(DatabaseProperty value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();

        if(value.getType().getField().matches("title|rich_text|date|people|files|checkbox|url|email|" +
                "phone_number|created_time|created_by|last_edited_time|last_edited_by")){
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

        typeSerializeRaw(value, gen, (ObjectMapper) gen.getCodec());

        gen.writeEndObject();
    }

    private void packageEmptyObjectType(DatabaseProperty value, JsonGenerator gen) throws IOException {
        gen.writeObjectFieldStart(value.getType().getField());
        gen.writeEndObject();
        gen.writeStringField(DatabaseProperty.NAME_FIELD, value.getName());
        gen.writeStringField(Property.ID_FIELD, value.getId());
        gen.writeStringField(Property.TYPE_FIELD, value.getType().getField());
    }

    @Override
    protected boolean checkTypeEquals(TypeUnit<DatabaseProperty> typeUnit, DatabaseProperty value) {
        return Objects.equals(typeUnit.getType(), value.getType().getField());
    }
}
