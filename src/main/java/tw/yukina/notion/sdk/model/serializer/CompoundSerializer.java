package tw.yukina.notion.sdk.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;
import lombok.Setter;
import tw.yukina.notion.sdk.model.deserializer.TypeUnit;
import tw.yukina.notion.sdk.model.endpoint.database.query.Compound;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.DatabasePropertyFilter;

import java.io.IOException;

public class CompoundSerializer extends AbstractSerializer<Compound> {

    public final static int LAYER_LIMIT = 2;

    @Getter
    @Setter
    public int layer = 1;

    @Override
    public void serialize(Compound value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(layer <= LAYER_LIMIT){
            gen.writeStartObject();
            gen.writeFieldName(value.getCompoundType().getField());
            gen.writeStartArray();
            for(DatabasePropertyFilter databasePropertyFilter: value.getDatabasePropertyFilters())
                serializePropertyFilter(databasePropertyFilter, gen, serializers);

            for(Compound compound: value.getCompounds()){
                CompoundSerializer compoundSerializer = new CompoundSerializer();
                compoundSerializer.setLayer(layer + 1);
                compoundSerializer.serialize(compound, gen, serializers);
            }
            gen.writeEndArray();
            gen.writeEndObject();
        } else {
            throw JsonMappingException.from(gen, "Compound filters just can be nested up to 2 levels deep, but now is "+ layer +" levels.");
        }
    }

    public void serializePropertyFilter(DatabasePropertyFilter databasePropertyFilter, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        DatabasePropertyFilterSerializer databasePropertyFilterSerializer = new DatabasePropertyFilterSerializer();
        databasePropertyFilterSerializer.serialize(databasePropertyFilter, gen, serializers);
    }

    @Override
    protected boolean checkTypeEquals(TypeUnit<Compound> typeUnit, Compound value) {
        return false;
    }
}
