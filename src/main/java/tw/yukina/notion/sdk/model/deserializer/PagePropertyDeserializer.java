package tw.yukina.notion.sdk.model.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.page.property.*;
import tw.yukina.notion.sdk.model.page.property.rollup.RollupProperty;

import java.io.IOException;

public class PagePropertyDeserializer extends AbstractDeserializer<PageProperty> {
    @Override
    public PageProperty deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(PropertyType.RICH_TEXT.getField(), RichTextProperty.class);
        addAvailableType(PropertyType.NUMBER.getField(), NumberProperty.class);
        addAvailableType(PropertyType.SELECT.getField(), SelectProperty.class);
        addAvailableType(PropertyType.MULTI_SELECT.getField(), MultiSelectProperty.class);
        addAvailableType(PropertyType.DATE.getField(), DateProperty.class);
        addAvailableType(PropertyType.FORMULA.getField(), FormulaProperty.class);
        addAvailableType(PropertyType.RELATION.getField(), RelationProperty.class);
        addAvailableType(PropertyType.ROLLUP.getField(), RollupProperty.class);
        addAvailableType(PropertyType.TITLE.getField(), TitleProperty.class);
        addAvailableType(PropertyType.PEOPLE.getField(), PeopleProperty.class);
        addAvailableType(PropertyType.FILES.getField(), FileProperty.class);
        addAvailableType(PropertyType.CHECKBOX.getField(), CheckboxProperty.class);
        addAvailableType(PropertyType.URL.getField(), UrlProperty.class);
        addAvailableType(PropertyType.EMAIL.getField(), EmailProperty.class);
        addAvailableType(PropertyType.PHONE_NUMBER.getField(), PhoneNumberProperty.class);
        addAvailableType(PropertyType.CREATED_TIME.getField(), CreatedTimeProperty.class);
        addAvailableType(PropertyType.CREATED_BY.getField(), CreatedByProperty.class);
        addAvailableType(PropertyType.LAST_EDITED_TIME.getField(), LastEditedTimeProperty.class);
        addAvailableType(PropertyType.LAST_EDITED_BY.getField(), LastEditedByProperty.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }
}
