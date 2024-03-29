package tw.yukina.notion.sdk.model.template.project;

import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.OptionColor;
import tw.yukina.notion.sdk.model.common.EmptyObject;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;
import tw.yukina.notion.sdk.model.common.date.Date;
import tw.yukina.notion.sdk.model.common.date.DateTime;
import tw.yukina.notion.sdk.model.common.date.DateTimeType;
import tw.yukina.notion.sdk.model.common.file.*;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.user.PersonObject;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.UserType;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.database.property.FormulaObject;
import tw.yukina.notion.sdk.model.database.property.*;
import tw.yukina.notion.sdk.model.helper.JsonNodeHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.helper.SelectOptionHelper;
import tw.yukina.notion.sdk.model.page.property.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

public final class Thing {

    public static DatabaseModel getDatabase(JsonNode responseJsonNode, DatabaseModel responseDatabaseModel) {
        DatabaseModel databaseModel = new DatabaseModel();
        databaseModel.setId(responseDatabaseModel.getId());
        databaseModel.setCreatedTime(responseDatabaseModel.getCreatedTime());
        databaseModel.setCreatedBy(responseDatabaseModel.getCreatedBy());
        databaseModel.setLastEditedTime(responseDatabaseModel.getLastEditedTime());
        databaseModel.setLastEditedBy(responseDatabaseModel.getLastEditedBy());
        databaseModel.setTitle(RichTextHelper.createDefaultArrayText("Thing (Test DB)"));
        databaseModel.setDescription(Collections.emptyList());
        databaseModel.setUrl(responseDatabaseModel.getUrl());
        databaseModel.setArchived(false);
        databaseModel.setInline(false);
        databaseModel.setParent(responseDatabaseModel.getParent());

        PageParent pageParent = new PageParent();
        pageParent.setParentType(ParentType.PAGE);
        pageParent.setPageId(JsonNodeHelper.getPageParentId(responseJsonNode));
        databaseModel.setParent(pageParent);

        Map<String, DatabaseProperty> propertyMap = new HashMap<>();
        databaseModel.setPropertyMap(propertyMap);

        tw.yukina.notion.sdk.model.database.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.database.property.RelationProperty();
        relationProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Related to Project (Test DB) (Property)"));
        relationProperty.setName("Related to Project (Test DB) (Property)");
        relationProperty.setType(PropertyType.RELATION);
        DualPropertyRelation dualPropertyRelation = new tw.yukina.notion.sdk.model.database.property.DualPropertyRelation();
        dualPropertyRelation.setType(RelationPropertyType.DUAL_PROPERTY);
        dualPropertyRelation.setDatabaseId(JsonNodeHelper.getDatabaseRelationDatabaseId(responseJsonNode, "Related to Project (Test DB) (Property)"));
        DualPropertyObject dualPropertyObject = new DualPropertyObject();
        dualPropertyObject.setSyncedPropertyName(JsonNodeHelper.getDatabaseRelationSyncedPropertyName(responseJsonNode, "Related to Project (Test DB) (Property)"));
        dualPropertyObject.setSyncedPropertyId(JsonNodeHelper.getDatabaseRelationSyncedPropertyId(responseJsonNode, "Related to Project (Test DB) (Property)"));

        dualPropertyRelation.setDualPropertyObject(dualPropertyObject);
        relationProperty.setRelationObject(dualPropertyRelation);
        propertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        relationProperty = new tw.yukina.notion.sdk.model.database.property.RelationProperty();
        relationProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Project (Test DB) - single_property"));
        relationProperty.setName("Project (Test DB) - single_property");
        relationProperty.setType(PropertyType.RELATION);
        SinglePropertyRelation singlePropertyRelation = new SinglePropertyRelation();
        singlePropertyRelation.setType(RelationPropertyType.SINGLE_PROPERTY);
        singlePropertyRelation.setEmptyObject(EmptyObject.of());
        singlePropertyRelation.setDatabaseId(JsonNodeHelper.getDatabaseRelationDatabaseId(responseJsonNode, "Project (Test DB) - single_property"));
        relationProperty.setRelationObject(singlePropertyRelation);
        propertyMap.put("Project (Test DB) - single_property", relationProperty);

        DatabaseProperty databaseProperty = new DatabaseProperty();
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

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Files"));
        databaseProperty.setName("Files");
        databaseProperty.setType(PropertyType.FILES);
        propertyMap.put("Files", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "URL"));
        databaseProperty.setName("URL");
        databaseProperty.setType(PropertyType.URL);
        propertyMap.put("URL", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "String"));
        databaseProperty.setName("String");
        databaseProperty.setType(PropertyType.RICH_TEXT);
        propertyMap.put("String", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Phone Number"));
        databaseProperty.setName("Phone Number");
        databaseProperty.setType(PropertyType.PHONE_NUMBER);
        propertyMap.put("Phone Number", databaseProperty);

//        tw.yukina.notion.sdk.model.database.property.RollupProperty rollupProperty = new tw.yukina.notion.sdk.model.database.property.RollupProperty();
//        rollupProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Project Name"));
//        rollupProperty.setName("Project Name");
//        rollupProperty.setType(PropertyType.ROLLUP);
//        RollupObject rollupObject = new RollupObject();
//        rollupObject.setRollupPropertyName("Intro");
//        rollupObject.setRelationPropertyName("Related to Project (Test DB) (Property)");
//        rollupObject.setRollupPropertyId("nTUH");
//        rollupObject.setRelationPropertyId(":YwF");
//        rollupObject.setRollupFunctionType(RollupFunctionType.SHOW_ORIGINAL);
//        rollupProperty.setRollupObject(rollupObject);
//        propertyMap.put("Project Name", rollupProperty);

        tw.yukina.notion.sdk.model.database.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.database.property.NumberProperty();
        numberProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Number"));
        numberProperty.setName("Number");
        numberProperty.setType(PropertyType.NUMBER);
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(NumberFormat.NUMBER);
        numberProperty.setNumberObject(numberObject);
        propertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
        multiSelectProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Tags"));
        multiSelectProperty.setName("Tags");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        SelectObject multiSelectObject = new SelectObject();
        multiSelectObject.setSelectOptions(new ArrayList<>());
        multiSelectProperty.setSelectObject(multiSelectObject);
        propertyMap.put("Tags", multiSelectProperty);

        multiSelectObject.setSelectOptions(JsonNodeHelper.getMultiSelectPropertyOptions(responseJsonNode, "Tags"));

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Last Edited By"));
        databaseProperty.setName("Last Edited By");
        databaseProperty.setType(PropertyType.LAST_EDITED_BY);
        propertyMap.put("Last Edited By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Created By"));
        databaseProperty.setName("Created By");
        databaseProperty.setType(PropertyType.CREATED_BY);
        propertyMap.put("Created By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Email"));
        databaseProperty.setName("Email");
        databaseProperty.setType(PropertyType.EMAIL);
        propertyMap.put("Email", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Done"));
        databaseProperty.setName("Done");
        databaseProperty.setType(PropertyType.CHECKBOX);
        propertyMap.put("Done", databaseProperty);

        tw.yukina.notion.sdk.model.database.property.FormulaProperty formulaProperty = new tw.yukina.notion.sdk.model.database.property.FormulaProperty();
        formulaProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "Formula"));
        formulaProperty.setName("Formula");
        formulaProperty.setType(PropertyType.FORMULA);
        FormulaObject formulaObject = new FormulaObject();
        formulaObject.setExpression("prop(\"Created\")");
        formulaProperty.setFormulaObject(formulaObject);
        propertyMap.put("Formula", formulaProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId(JsonNodeHelper.getPropertyId(responseJsonNode, "DeadLine"));
        databaseProperty.setName("DeadLine");
        databaseProperty.setType(PropertyType.DATE);
        propertyMap.put("DeadLine", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("title");
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        return databaseModel;
    }

    public static Map<String, DatabaseProperty> getCreateDatabaseProperty(String projectId) {

        Map<String, DatabaseProperty> propertyMap = new HashMap<>();

        DatabaseProperty databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        tw.yukina.notion.sdk.model.database.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.database.property.RelationProperty();
        relationProperty.setName("Related to Project (Test DB) (Property)");
        relationProperty.setType(PropertyType.RELATION);
        tw.yukina.notion.sdk.model.database.property.RelationObject relationObject = new tw.yukina.notion.sdk.model.database.property.RelationObject();
        relationObject.setDatabaseId(projectId);
        relationProperty.setRelationObject(relationObject);
        propertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

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

        selectObject.setSelectOptions(SelectOptionHelper.getSelectOptions("Doc", "Todo", "Event"));

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Files");
        databaseProperty.setType(PropertyType.FILES);
        propertyMap.put("Files", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("URL");
        databaseProperty.setType(PropertyType.URL);
        propertyMap.put("URL", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("String");
        databaseProperty.setType(PropertyType.RICH_TEXT);
        propertyMap.put("String", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Phone Number");
        databaseProperty.setType(PropertyType.PHONE_NUMBER);
        propertyMap.put("Phone Number", databaseProperty);

//        tw.yukina.notion.sdk.model.database.property.RollupProperty rollupProperty = new tw.yukina.notion.sdk.model.database.property.RollupProperty();
//        rollupProperty.setId("c%3A%5BL");
//        rollupProperty.setName("Project Name");
//        rollupProperty.setType(PropertyType.ROLLUP);
//        RollupObject rollupObject = new RollupObject();
//        rollupObject.setRollupPropertyName("Intro");
//        rollupObject.setRelationPropertyName("Related to Project (Test DB) (Property)");
//        rollupObject.setRollupPropertyId("nTUH");
//        rollupObject.setRelationPropertyId(":YwF");
//        rollupObject.setRollupFunctionType(RollupFunctionType.SHOW_ORIGINAL);
//        rollupProperty.setRollupObject(rollupObject);
//        propertyMap.put("Project Name", rollupProperty);

        tw.yukina.notion.sdk.model.database.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.database.property.NumberProperty();
        numberProperty.setName("Number");
        numberProperty.setType(PropertyType.NUMBER);
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(NumberFormat.NUMBER);
        numberProperty.setNumberObject(numberObject);
        propertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
        multiSelectProperty.setName("Tags");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        SelectObject multiSelectObject = new SelectObject();
        multiSelectObject.setSelectOptions(new ArrayList<>());
        multiSelectProperty.setSelectObject(multiSelectObject);
        propertyMap.put("Tags", multiSelectProperty);

        multiSelectObject.setSelectOptions(SelectOptionHelper.getSelectOptions("Index", "Coding", "Listener", "Outdated"));

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Last Edited By");
        databaseProperty.setType(PropertyType.LAST_EDITED_BY);
        propertyMap.put("Last Edited By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Created By");
        databaseProperty.setType(PropertyType.CREATED_BY);
        propertyMap.put("Created By", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Email");
        databaseProperty.setType(PropertyType.EMAIL);
        propertyMap.put("Email", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("Done");
        databaseProperty.setType(PropertyType.CHECKBOX);
        propertyMap.put("Done", databaseProperty);

        tw.yukina.notion.sdk.model.database.property.FormulaProperty formulaProperty = new tw.yukina.notion.sdk.model.database.property.FormulaProperty();
        formulaProperty.setName("Formula");
        formulaProperty.setType(PropertyType.FORMULA);
        FormulaObject formulaObject = new FormulaObject();
        formulaObject.setExpression("prop(\"Created\")");
        formulaProperty.setFormulaObject(formulaObject);
        propertyMap.put("Formula", formulaProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setName("DeadLine");
        databaseProperty.setType(PropertyType.DATE);
        propertyMap.put("DeadLine", databaseProperty);

        databaseProperty = new DatabaseProperty();
        databaseProperty.setId("title");
        databaseProperty.setName("Name");
        databaseProperty.setType(PropertyType.TITLE);
        propertyMap.put("Name", databaseProperty);

        return propertyMap;
    }

    public static Map<String, PageProperty> getCreatePageProperty() throws MalformedURLException {
        Map<String, PageProperty> pagePropertyMap = new LinkedHashMap<>();

        tw.yukina.notion.sdk.model.page.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.page.property.RelationProperty();
        relationProperty.setId("HR%5DF");
        relationProperty.setType(PropertyType.RELATION);
        List<tw.yukina.notion.sdk.model.page.property.RelationObject> relationObjects = new ArrayList<>();
        tw.yukina.notion.sdk.model.page.property.RelationObject relationObject = new tw.yukina.notion.sdk.model.page.property.RelationObject();
        relationObject.setId("94252720947249cf97019da861122281");
        relationObjects.add(relationObject);
        relationProperty.setRelation(relationObjects);
        pagePropertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        tw.yukina.notion.sdk.model.page.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.page.property.SelectProperty();
        selectProperty.setId("jaki");
        selectProperty.setType(PropertyType.SELECT);
        SelectOption selectOption = new SelectOption();
        selectOption.setId("af06365d-4fcc-49d5-8ae4-63e55a186335");
        selectOption.setName("Doc");
        selectOption.setColor(OptionColor.ORANGE);
        selectProperty.setSelectOption(selectOption);
        pagePropertyMap.put("Type", selectProperty);

        UrlProperty urlProperty = new UrlProperty();
        urlProperty.setId("k%3BZB");
        urlProperty.setType(PropertyType.URL);
        urlProperty.setUrl(new URL("https://www.google.com"));
        pagePropertyMap.put("URL", urlProperty);

        RichTextProperty textProperty = new RichTextProperty();
        textProperty.setId("cfjR");
        textProperty.setType(PropertyType.RICH_TEXT);
        List<RichText> texts = RichTextHelper.createDefaultArrayText("This is an apple.");
        textProperty.setTexts(texts);
        pagePropertyMap.put("String", textProperty);

        PhoneNumberProperty phoneNumberProperty = new PhoneNumberProperty();
        phoneNumberProperty.setId("lrsp");
        phoneNumberProperty.setType(PropertyType.PHONE_NUMBER);
        phoneNumberProperty.setPhoneNumber("0912345678");
        pagePropertyMap.put("Phone Number", phoneNumberProperty);

        tw.yukina.notion.sdk.model.page.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.page.property.NumberProperty();
        numberProperty.setId("%3DF%3Cr");
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumber(0.12345678901234568);
        pagePropertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.page.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.page.property.MultiSelectProperty();
        multiSelectProperty.setId("GZVL");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        List<SelectOption> selectOptions = new ArrayList<>();
        selectOption = new SelectOption();
        selectOption.setId("550102d4-ae3d-4000-a433-e3685f7cbde4");
        selectOption.setName("Index");
        selectOption.setColor(OptionColor.GRAY);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("83c556bf-adfb-4f53-a269-cddaf48b8e90");
        selectOption.setName("Listener");
        selectOption.setColor(OptionColor.GREEN);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("4412d9b5-1464-4cb8-bb49-e9bfa6361df9");
        selectOption.setName("Coding");
        selectOption.setColor(OptionColor.ORANGE);
        selectOptions.add(selectOption);
        multiSelectProperty.setSelectOptions(selectOptions);
        pagePropertyMap.put("Tags", multiSelectProperty);

        EmailProperty emailProperty = new EmailProperty();
        emailProperty.setId("I~Ca");
        emailProperty.setType(PropertyType.EMAIL);
        emailProperty.setEmail("1p41p4jejo@gmail.com");
        pagePropertyMap.put("Email", emailProperty);

        CheckboxProperty checkboxProperty = new CheckboxProperty();
        checkboxProperty.setId("gF%7B~");
        checkboxProperty.setType(PropertyType.CHECKBOX);
        checkboxProperty.setCheckbox(true);
        pagePropertyMap.put("Done", checkboxProperty);

        DateProperty dateProperty = new DateProperty();
        dateProperty.setId("yQxw");
        dateProperty.setType(PropertyType.DATE);
        tw.yukina.notion.sdk.model.common.date.Date date = new Date();
        date.setDateTimeType(DateTimeType.DATE);
        date.setStart(LocalDate.parse("2021-09-24"));
        dateProperty.setDateTimeProperty(date);
        pagePropertyMap.put("DeadLine", dateProperty);

        TitleProperty titleProperty = new TitleProperty();
        titleProperty.setId("title");
        titleProperty.setType(PropertyType.TITLE);
        titleProperty.setTexts(RichTextHelper.createDefaultArrayText("Hi I'm Test"));
        pagePropertyMap.put("Name", titleProperty);

        return pagePropertyMap;
    }

    public static Map<String, PageProperty> getPageProperty(JsonNode responseJsonNode) throws MalformedURLException {
        Map<String, PageProperty> pagePropertyMap = new LinkedHashMap<>();

        tw.yukina.notion.sdk.model.page.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.page.property.RelationProperty();
        relationProperty.setId("HR%5DF");
        relationProperty.setType(PropertyType.RELATION);
        List<tw.yukina.notion.sdk.model.page.property.RelationObject> relationObjects = new ArrayList<>();
        tw.yukina.notion.sdk.model.page.property.RelationObject relationObject = new tw.yukina.notion.sdk.model.page.property.RelationObject();
        relationObject.setId("94252720-9472-49cf-9701-9da861122281");
        relationObjects.add(relationObject);
        relationProperty.setRelation(relationObjects);
        pagePropertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        relationProperty = new tw.yukina.notion.sdk.model.page.property.RelationProperty();
        relationProperty.setId("TdbY");
        relationProperty.setType(PropertyType.RELATION);
        relationObjects = new ArrayList<>();
        relationObject = new tw.yukina.notion.sdk.model.page.property.RelationObject();
        relationObject.setId("94252720-9472-49cf-9701-9da861122281");
        relationObjects.add(relationObject);
        relationProperty.setRelation(relationObjects);
        pagePropertyMap.put("Project (Test DB) - single_property", relationProperty);

        CreatedTimeProperty createdTimeProperty = new CreatedTimeProperty();
        createdTimeProperty.setId("EAsy");
        createdTimeProperty.setType(PropertyType.CREATED_TIME);
        createdTimeProperty.setCreatedTime(ZonedDateTime.parse("2021-10-11T00:07:00.000Z"));
        pagePropertyMap.put("Created", createdTimeProperty);

        LastEditedTimeProperty lastEditedTimeProperty = new LastEditedTimeProperty();
        lastEditedTimeProperty.setId("ouE%3C");
        lastEditedTimeProperty.setType(PropertyType.LAST_EDITED_TIME);
        lastEditedTimeProperty.setLastEditedTime(ZonedDateTime.parse(responseJsonNode
                .get("properties").get("Last Edited").get("last_edited_time").asText()));
        pagePropertyMap.put("Last Edited", lastEditedTimeProperty);

        tw.yukina.notion.sdk.model.page.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.page.property.SelectProperty();
        selectProperty.setId("jaki");
        selectProperty.setType(PropertyType.SELECT);
        SelectOption selectOption = new SelectOption();
        selectOption.setId("af06365d-4fcc-49d5-8ae4-63e55a186335");
        selectOption.setName("Doc");
        selectOption.setColor(OptionColor.ORANGE);
        selectProperty.setSelectOption(selectOption);
        pagePropertyMap.put("Type", selectProperty);

        FileProperty fileProperty = new FileProperty();
        fileProperty.setId("u%5CoN");
        fileProperty.setType(PropertyType.FILES);
        List<FileObject> fileObjects = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            NotionSourceFile notionSourceFile = new NotionSourceFile();
            notionSourceFile.setFileType(FileType.FILE);
            notionSourceFile.setName(responseJsonNode
                    .get("properties").get("Files").get("files").get(i).get("name").asText());
            NotionSourceFileObject notionSourceFileObject = new NotionSourceFileObject();
            notionSourceFileObject.setExpiryTime(ZonedDateTime.parse(responseJsonNode
                    .get("properties").get("Files").get("files").get(i).get("file").get("expiry_time").asText()));
            notionSourceFileObject.setUrl(new URL(responseJsonNode
                    .get("properties").get("Files").get("files").get(i).get("file").get("url").asText()));
            notionSourceFile.setNotionSourceFileObject(notionSourceFileObject);

            fileObjects.add(notionSourceFile);
        }

        ExternalSourceFile externalSourceFile = new ExternalSourceFile();
        externalSourceFile.setFileType(FileType.EXTERNAL);
        externalSourceFile.setName("https://i.imgur.com/7AOa7xl.jpg");
        ExternalSourceFileObject externalSourceFileObject = new ExternalSourceFileObject();
        externalSourceFileObject.setUrl(new URL("https://i.imgur.com/7AOa7xl.jpg"));
        externalSourceFile.setExternalSourceFileObject(externalSourceFileObject);
        fileObjects.add(externalSourceFile);
        fileProperty.setFileObjects(fileObjects);
        pagePropertyMap.put("Files", fileProperty);

        UrlProperty urlProperty = new UrlProperty();
        urlProperty.setId("k%3BZB");
        urlProperty.setType(PropertyType.URL);
        urlProperty.setUrl(new URL("https://www.notion.so/Thing1-e8df4eb760e047b88808974fb12b0ef3"));
        pagePropertyMap.put("URL", urlProperty);

        RichTextProperty textProperty = new RichTextProperty();
        textProperty.setId("cfjR");
        textProperty.setType(PropertyType.RICH_TEXT);
        List<RichText> texts = RichTextHelper.createDefaultArrayText("This is an apple.");
        textProperty.setTexts(texts);
        pagePropertyMap.put("String", textProperty);

        PhoneNumberProperty phoneNumberProperty = new PhoneNumberProperty();
        phoneNumberProperty.setId("lrsp");
        phoneNumberProperty.setType(PropertyType.PHONE_NUMBER);
        phoneNumberProperty.setPhoneNumber("0912345678");
        pagePropertyMap.put("Phone Number", phoneNumberProperty);

        tw.yukina.notion.sdk.model.page.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.page.property.NumberProperty();
        numberProperty.setId("%3DF%3Cr");
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumber(0.12345678901234568);
        pagePropertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.page.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.page.property.MultiSelectProperty();
        multiSelectProperty.setId("GZVL");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        List<SelectOption> selectOptions = new ArrayList<>();
        selectOption = new SelectOption();
        selectOption.setId("550102d4-ae3d-4000-a433-e3685f7cbde4");
        selectOption.setName("Index");
        selectOption.setColor(OptionColor.GRAY);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("4412d9b5-1464-4cb8-bb49-e9bfa6361df9");
        selectOption.setName("Coding");
        selectOption.setColor(OptionColor.ORANGE);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("83c556bf-adfb-4f53-a269-cddaf48b8e90");
        selectOption.setName("Listener");
        selectOption.setColor(OptionColor.GREEN);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("b32f4ded-7be6-4e3f-a291-ba3892320d77");
        selectOption.setName("Outdated");
        selectOption.setColor(OptionColor.BROWN);
        selectOptions.add(selectOption);

        multiSelectProperty.setSelectOptions(selectOptions);
        pagePropertyMap.put("Tags", multiSelectProperty);

        LastEditedByProperty lastEditedByProperty = new LastEditedByProperty();
        lastEditedByProperty.setId("wy%3CX");
        lastEditedByProperty.setType(PropertyType.LAST_EDITED_BY);
        PersonUser personUser = new PersonUser();
        personUser.setId("4640eada-22ca-47be-9093-07524a2e777b");
        personUser.setName("望月知");
        personUser.setAvatar(new URL("https://s3-us-west-2.amazonaws.com/public.notion-static.com/1978afb7-35bb-4111-9d2f-3c67c7d00b13/FB_IMG_1594677191662_(1).jpg"));
        personUser.setUserType(UserType.PERSON);
        PersonObject person = new PersonObject();
        person.setEmail("1p41p4jejo@gmail.com");
        personUser.setPerson(person);
        lastEditedByProperty.setUsers(personUser);
        pagePropertyMap.put("Last Edited By", lastEditedByProperty);

        CreatedByProperty createdByProperty = new CreatedByProperty();
        createdByProperty.setId("BPP%5C");
        createdByProperty.setType(PropertyType.CREATED_BY);
        createdByProperty.setUsers(personUser);
        pagePropertyMap.put("Created By", createdByProperty);

        EmailProperty emailProperty = new EmailProperty();
        emailProperty.setId("I~Ca");
        emailProperty.setType(PropertyType.EMAIL);
        emailProperty.setEmail("1p41p4jejo@gmail.com");
        pagePropertyMap.put("Email", emailProperty);

        CheckboxProperty checkboxProperty = new CheckboxProperty();
        checkboxProperty.setId("gF%7B~");
        checkboxProperty.setType(PropertyType.CHECKBOX);
        checkboxProperty.setCheckbox(true);
        pagePropertyMap.put("Done", checkboxProperty);

        tw.yukina.notion.sdk.model.page.property.FormulaProperty formulaProperty = new tw.yukina.notion.sdk.model.page.property.FormulaProperty();
        formulaProperty.setId("X%7BN%3B");
        formulaProperty.setType(PropertyType.FORMULA);
        FormulaDateObject formulaDateObject = new FormulaDateObject();
        formulaDateObject.setFormulaType(FormulaType.DATE);
        DateTime dateTime = new DateTime();
        dateTime.setDateTimeType(DateTimeType.DATE_TIME);
        dateTime.setStart(ZonedDateTime.parse("2021-10-11T00:07:00.000+00:00"));
        formulaDateObject.setDateTimeProperty(dateTime);
        formulaProperty.setFormula(formulaDateObject);
        pagePropertyMap.put("Formula", formulaProperty);

        DateProperty dateProperty = new DateProperty();
        dateProperty.setId("yQxw");
        dateProperty.setId("yQxw");
        dateProperty.setType(PropertyType.DATE);
        tw.yukina.notion.sdk.model.common.date.Date date = new Date();
        date.setDateTimeType(DateTimeType.DATE);
        date.setStart(LocalDate.parse("2021-09-24"));
        dateProperty.setDateTimeProperty(date);
        pagePropertyMap.put("DeadLine", dateProperty);

        TitleProperty titleProperty = new TitleProperty();
        titleProperty.setId("title");
        titleProperty.setType(PropertyType.TITLE);
        titleProperty.setTexts(RichTextHelper.createDefaultArrayText("Page 1（Never Edit）"));
        pagePropertyMap.put("Name", titleProperty);

        return pagePropertyMap;
    }
}
