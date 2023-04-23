package tw.yukina.notion.sdk.model.template.project;

import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.database.property.DatabaseProperty;
import tw.yukina.notion.sdk.model.database.property.SelectObject;
import tw.yukina.notion.sdk.model.helper.JsonNodeHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.helper.SelectOptionHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Project {

    public static Map<String, DatabaseProperty> getCreateDatabaseProperty(){
        Map<String, DatabaseProperty> propertyMap = new HashMap<>();

        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Created");
        databaseProperty.setType(PropertyType.CREATED_TIME);
        propertyMap.put("Created", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Last Edited");
        databaseProperty.setType(PropertyType.LAST_EDITED_TIME);
        propertyMap.put("Last Edited", databaseProperty);

        tw.yukina.notion.sdk.model.database.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.database.property.SelectProperty();
        selectProperty.setName("Type");
        selectProperty.setType(PropertyType.SELECT);
        SelectObject selectObject = new SelectObject();
        selectObject.setSelectOptions(new ArrayList<>());
        selectProperty.setSelectObject(selectObject);
        propertyMap.put("Type", selectProperty);

        selectObject.setSelectOptions(SelectOptionHelper.getSelectOptions(new String[]{"Milestone", "Study Plan", "Interest", "Trivia"}));

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
        multiSelectProperty.setName("Tags");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        SelectObject multiSelectObject = new SelectObject();
        multiSelectObject.setSelectOptions(new ArrayList<>());
        multiSelectProperty.setSelectObject(multiSelectObject);
        propertyMap.put("Tags", multiSelectProperty);

        multiSelectObject.setSelectOptions(SelectOptionHelper.getSelectOptions(new String[]{"Tag1", "Tag2", "Tag3"}));

        return propertyMap;
    }

    public static DatabaseModel getDatabase(JsonNode responseJsonNode, DatabaseModel responseDatabaseModel){
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setId(responseDatabaseModel.getId());
        databaseModel.setCreatedTime(responseDatabaseModel.getCreatedTime());
        databaseModel.setLastEditedTime(responseDatabaseModel.getLastEditedTime());
        databaseModel.setTitle(RichTextHelper.createDefaultArrayText("Project (Test DB)"));
        databaseModel.setUrl(responseDatabaseModel.getUrl());

        PageParent pageParent = new PageParent();
        pageParent.setParentType(ParentType.PAGE);
        pageParent.setPageId(JsonNodeHelper.getPageParentId(responseJsonNode));
        databaseModel.setParent(pageParent);

        Map<String, DatabaseProperty> propertyMap = new HashMap<>();
        databaseModel.setPropertyMap(propertyMap);

        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Name"));
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Created"));
        databaseProperty.setName("Created");
        databaseProperty.setType(PropertyType.CREATED_TIME);
        propertyMap.put("Created", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Last Edited"));
        databaseProperty.setName("Last Edited");
        databaseProperty.setType(PropertyType.LAST_EDITED_TIME);
        propertyMap.put("Last Edited", databaseProperty);

        tw.yukina.notion.sdk.model.database.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.database.property.SelectProperty();
        selectProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Type"));
        selectProperty.setName("Type");
        selectProperty.setType(PropertyType.SELECT);
        SelectObject selectObject = new SelectObject();
        selectObject.setSelectOptions(new ArrayList<>());
        selectProperty.setSelectObject(selectObject);
        propertyMap.put("Type", selectProperty);

        selectObject.setSelectOptions(JsonNodeHelper.getSelectPropertyOptions(responseJsonNode, "Type"));

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
        multiSelectProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Tags"));
        multiSelectProperty.setName("Tags");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        SelectObject multiSelectObject = new SelectObject();
        multiSelectObject.setSelectOptions(new ArrayList<>());
        multiSelectProperty.setSelectObject(multiSelectObject);
        propertyMap.put("Tags", multiSelectProperty);

        multiSelectObject.setSelectOptions(JsonNodeHelper.getMultiSelectPropertyOptions(responseJsonNode, "Tags"));

        return databaseModel;
    }
}
