package tw.yukina.notion.sdk.model.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.OptionColor;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.database.property.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest extends ModelTest {

    @Test
    void databaseTest() throws IOException {
        Response response = getResponse( BASE_URL + "/databases/31d2e694db36482298a47dfa29cde551");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Database responseDatabase = readValueUseCommonObjectMapper(tree, Database.class);

        Database database = new Database();
        database.setId(responseDatabase.getId());
        database.setCreatedTime(responseDatabase.getCreatedTime());
        database.setLastEditedTime(responseDatabase.getLastEditedTime());
        database.setTitle(RichTextHelper.createDefaultArrayText("Things (Test DB)"));
        database.setUrl(responseDatabase.getUrl());

        PageParent pageParent = new PageParent();
        pageParent.setParentType(ParentType.PAGE);
        pageParent.setPageId("8d0f791d-c0fc-4ee6-bc7f-27fe0a623cad");
        database.setParent(pageParent);

        Map<String, DatabaseProperty> propertyMap = new HashMap<>();
        database.setPropertyMap(propertyMap);

        RelationProperty relationProperty = new RelationProperty();
        relationProperty.setId("%3AYwF");
        relationProperty.setName("Related to Project (Test DB) (Property)");
        relationProperty.setType(PropertyType.RELATION);
        RelationObject relationObject = new RelationObject();
        relationObject.setDatabaseId("8140870d-e48d-4e3d-8496-1647c7810b11");
        relationObject.setSyncedPropertyName("Things");
        relationObject.setSyncedPropertyId("acq^");
        relationProperty.setRelationObject(relationObject);
        propertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setId("%3D%3BcI");
        databaseProperty.setName("Created");
        databaseProperty.setType(PropertyType.CREATED_TIME);
        propertyMap.put("Created", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("%3DKdr");
        databaseProperty.setName("Last Edited");
        databaseProperty.setType(PropertyType.LAST_EDITED_TIME);
        propertyMap.put("Last Edited", databaseProperty);

        SelectProperty selectProperty = new SelectProperty();
        selectProperty.setId("K%5CXF");
        selectProperty.setName("Type");
        selectProperty.setType(PropertyType.SELECT);
        SelectObject selectObject = new SelectObject();
        selectObject.setSelectOptions(new ArrayList<>());
        selectProperty.setSelectObject(selectObject);
        propertyMap.put("Type", selectProperty);

        ArrayNode arrayNode = (ArrayNode) responseJsonNode.get("properties").get("Type").get("select").get("options");

        for(JsonNode jsonNode : arrayNode){
            SelectOption selectOption = new SelectOption();
            selectOption.setId(jsonNode.get("id").asText());
            selectOption.setName(jsonNode.get("name").asText());
            selectOption.setColor(OptionColor.getEnum(jsonNode.get("color").asText()));
            selectObject.getSelectOptions().add(selectOption);
        }

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("KdI%3D");
        databaseProperty.setName("Files");
        databaseProperty.setType(PropertyType.FILES);
        propertyMap.put("Files", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("R%60gV");
        databaseProperty.setName("URL");
        databaseProperty.setType(PropertyType.URL);
        propertyMap.put("URL", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("Wo%5Dg");
        databaseProperty.setName("String");
        databaseProperty.setType(PropertyType.RICH_TEXT);
        propertyMap.put("String", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("%5EF_W");
        databaseProperty.setName("Phone Number");
        databaseProperty.setType(PropertyType.PHONE_NUMBER);
        propertyMap.put("Phone Number", databaseProperty);

        RollupProperty rollupProperty = new RollupProperty();
        rollupProperty.setId("c%3A%5BL");
        rollupProperty.setName("Project Name");
        rollupProperty.setType(PropertyType.ROLLUP);
        RollupObject rollupObject = new RollupObject();
        rollupObject.setRollupPropertyName("Intro");
        rollupObject.setRelationPropertyName("Related to Project (Test DB) (Property)");
        rollupObject.setRollupPropertyId("nTUH");
        rollupObject.setRelationPropertyId(":YwF");
        rollupObject.setRollupFunctionType(RollupFunctionType.SHOW_ORIGINAL);
        rollupProperty.setRollupObject(rollupObject);
        propertyMap.put("Project Name", rollupProperty);

        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setId("ilF%5B");
        numberProperty.setName("Number");
        numberProperty.setType(PropertyType.NUMBER);
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(NumberFormat.NUMBER);
        numberProperty.setNumberObject(numberObject);
        propertyMap.put("Number", numberProperty);

        MultiSelectProperty multiSelectProperty = new MultiSelectProperty();
        multiSelectProperty.setId("lJ%7BX");
        multiSelectProperty.setName("Tags");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        MultiSelectObject multiSelectObject = new MultiSelectObject();
        multiSelectObject.setSelectOptions(new ArrayList<>());
        multiSelectProperty.setMultiSelectObject(multiSelectObject);
        propertyMap.put("Tags", multiSelectProperty);

        arrayNode = (ArrayNode) responseJsonNode.get("properties").get("Tags").get("multi_select").get("options");

        for(JsonNode jsonNode : arrayNode){
            SelectOption selectOption = new SelectOption();
            selectOption.setId(jsonNode.get("id").asText());
            selectOption.setName(jsonNode.get("name").asText());
            selectOption.setColor(OptionColor.getEnum(jsonNode.get("color").asText()));
            multiSelectObject.getSelectOptions().add(selectOption);
        }

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("nsY%3D");
        databaseProperty.setName("Last Edited By");
        databaseProperty.setType(PropertyType.LAST_EDITED_BY);
        propertyMap.put("Last Edited By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("onjH");
        databaseProperty.setName("Created By");
        databaseProperty.setType(PropertyType.CREATED_BY);
        propertyMap.put("Created By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("xCs%5E");
        databaseProperty.setName("Email");
        databaseProperty.setType(PropertyType.EMAIL);
        propertyMap.put("Email", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("%7Bf%3Ec");
        databaseProperty.setName("Done");
        databaseProperty.setType(PropertyType.CHECKBOX);
        propertyMap.put("Done", databaseProperty);

        FormulaProperty formulaProperty = new FormulaProperty();
        formulaProperty.setId("%7DgjI");
        formulaProperty.setName("Formula");
        formulaProperty.setType(PropertyType.FORMULA);
        FormulaObject formulaObject = new FormulaObject();
        formulaObject.setExpression("prop(\"Created\")");
        formulaProperty.setFormulaObject(formulaObject);
        propertyMap.put("Formula", formulaProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("~Q%40z");
        databaseProperty.setName("DeadLine");
        databaseProperty.setType(PropertyType.DATE);
        propertyMap.put("DeadLine", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("title");
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(database);

        assertEquals(responseDatabase, database);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}
