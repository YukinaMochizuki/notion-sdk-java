package tw.yukina.notion.sdk.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import tw.yukina.notion.sdk.model.deserializer.TypeUnit;
import tw.yukina.notion.sdk.model.endpoint.database.query.Compound;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.DatabasePropertyFilter;

import java.io.IOException;

public class CompoundSerializer extends AbstractSerializer<Compound> {

    public final static int LAYER_LIMIT = 2;

    public void callSerialize(int layer, Compound value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

    }

    @Override
    public void serialize(Compound value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        ObjectMapper objectMapper = (ObjectMapper) gen.getCodec();

        gen.writeStartObject();
        gen.writeFieldName(value.getCompoundType().getField());
        gen.writeStartArray();

        for(DatabasePropertyFilter databasePropertyFilter: value.getDatabasePropertyFilters()){
            DatabasePropertyFilterSerializer databasePropertyFilterSerializer = new DatabasePropertyFilterSerializer();
            databasePropertyFilterSerializer.serialize(databasePropertyFilter, gen, serializers);
        }

        gen.writeEndArray();
        gen.writeEndObject();
    }

    @Override
    protected boolean checkTypeEquals(TypeUnit<Compound> typeUnit, Compound value) {
        return false;
    }
}
