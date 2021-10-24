package tw.yukina.notion.sdk.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import tw.yukina.notion.sdk.model.deserializer.TypeUnit;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.*;

import java.io.IOException;
import java.util.Objects;

public class DatabasePropertyFilterSerializer extends AbstractSerializer<DatabasePropertyFilter> {

    @Override
    public void serialize(DatabasePropertyFilter value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        addAutoSerializeFilter();
        typeSerialize(value, gen);
    }

    public void addAutoSerializeFilter(){
        addAvailableType("TitlePropertyFilter", TitlePropertyFilter.class);
        addAvailableType("RichTextPropertyFilter", RichTextPropertyFilter.class);
        addAvailableType("UrlPropertyFilter", UrlPropertyFilter.class);
        addAvailableType("PhonePropertyFilter", PhonePropertyFilter.class);

        addAvailableType("SelectPropertyFilter", SelectPropertyFilter.class);
        addAvailableType("MultiSelectPropertyFilter", MultiSelectPropertyFilter.class);

        addAvailableType("PeoplePropertyFilter", PeoplePropertyFilter.class);
        addAvailableType("RelationPropertyFilter", RelationPropertyFilter.class);

        addAvailableType("CheckboxPropertyFilter", CheckboxPropertyFilter.class);
//        addAvailableType("DatePropertyFilter", DatePropertyFilter.class);
        addAvailableType("FilesPropertyFilter", FilesPropertyFilter.class);
        addAvailableType("FormulaPropertyFilter", FormulaPropertyFilter.class);
    }

    @Override
    protected boolean checkTypeEquals(TypeUnit<DatabasePropertyFilter> typeUnit, DatabasePropertyFilter value) {
        return Objects.equals(typeUnit.getType(), value.getClass().getSimpleName());
    }
}
