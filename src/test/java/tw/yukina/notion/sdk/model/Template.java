package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.Block;
import tw.yukina.notion.sdk.model.block.BlockHelper;
import tw.yukina.notion.sdk.model.block.list.ListBlockHelper;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;
import tw.yukina.notion.sdk.model.common.date.Date;
import tw.yukina.notion.sdk.model.common.date.DateTime;
import tw.yukina.notion.sdk.model.common.date.DateTimeType;
import tw.yukina.notion.sdk.model.common.file.*;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.common.user.PersonObject;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.UserType;
import tw.yukina.notion.sdk.model.database.Database;
import tw.yukina.notion.sdk.model.database.property.*;
import tw.yukina.notion.sdk.model.database.property.FormulaObject;
import tw.yukina.notion.sdk.model.page.property.*;
import tw.yukina.notion.sdk.model.page.property.FormulaProperty;
import tw.yukina.notion.sdk.model.page.property.MultiSelectProperty;
import tw.yukina.notion.sdk.model.page.property.NumberProperty;
import tw.yukina.notion.sdk.model.page.property.RelationObject;
import tw.yukina.notion.sdk.model.page.property.RelationProperty;
import tw.yukina.notion.sdk.model.page.property.SelectProperty;
import tw.yukina.notion.sdk.model.page.property.rollup.ArrayRollupProperty;
import tw.yukina.notion.sdk.model.page.property.rollup.RollupProperty;
import tw.yukina.notion.sdk.model.page.property.rollup.RollupType;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

public class Template {

    @NotNull
    public static List<Block> getPageBlock() throws Exception {
        List<Block> blocks = new ArrayList<>();

        Block addBlock = BlockHelper.createDefaultParagraph("Text");
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 1"));
        blocks.add(addBlock);

        addBlock = ListBlockHelper
                .createDefaultBulletedList(RichTextHelper.createDefaultArrayText("bulleted List 2"));
        blocks.add(addBlock);

        RichText text = RichTextHelper.createDateMention("2021-08-21");
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        text = RichTextHelper.createDateMention("2021-08-10T10:00:00.000+08:00", "2021-08-28T14:23:00.000+08:00");
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        text = RichTextHelper.createDefaultText("public static void main(String[] args)");
        text.getAnnotations().setCode(true);
        addBlock = BlockHelper.createDefaultParagraph(text);
        blocks.add(addBlock);

        return blocks;
    }

    public static Map<String, PageProperty> getPagePropertyTemplate(JsonNode responseJsonNode) throws MalformedURLException {
        Map<String, PageProperty> pagePropertyMap = new LinkedHashMap<>();

        RelationProperty relationProperty = new RelationProperty();
        relationProperty.setId("%3AYwF");
        relationProperty.setType(PropertyType.RELATION);
        List<RelationObject> relationObjects = new ArrayList<>();
        RelationObject relationObject = new RelationObject();
        relationObject.setId("f34b7514-78ec-40e7-afb1-16dda0142315");
        relationObjects.add(relationObject);
        relationProperty.setRelation(relationObjects);
        pagePropertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        CreatedTimeProperty createdTimeProperty = new CreatedTimeProperty();
        createdTimeProperty.setId("%3D%3BcI");
        createdTimeProperty.setType(PropertyType.CREATED_TIME);
        createdTimeProperty.setCreatedTime(ZonedDateTime.parse("2021-09-07T09:23:00.000Z"));
        pagePropertyMap.put("Created", createdTimeProperty);

        LastEditedTimeProperty lastEditedTimeProperty = new LastEditedTimeProperty();
        lastEditedTimeProperty.setId("%3DKdr");
        lastEditedTimeProperty.setType(PropertyType.LAST_EDITED_TIME);
        lastEditedTimeProperty.setLastEditedTime(ZonedDateTime.parse(responseJsonNode
                .get("properties").get("Last Edited").get("last_edited_time").asText()));
        pagePropertyMap.put("Last Edited", lastEditedTimeProperty);

        SelectProperty selectProperty = new SelectProperty();
        selectProperty.setId("K%5CXF");
        selectProperty.setType(PropertyType.SELECT);
        SelectOption selectOption = new SelectOption();
        selectOption.setId("94fe6b2c-c8bf-4d27-9285-3c2632d85b85");
        selectOption.setName("Doc");
        selectOption.setColor(OptionColor.GREEN);
        selectProperty.setSelectOption(selectOption);
        pagePropertyMap.put("Type", selectProperty);

        FileProperty fileProperty = new FileProperty();
        fileProperty.setId("KdI%3D");
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
        externalSourceFile.setName(responseJsonNode
                .get("properties").get("Files").get("files").get(2).get("name").asText());
        ExternalSourceFileObject externalSourceFileObject = new ExternalSourceFileObject();
        externalSourceFileObject.setUrl(new URL(responseJsonNode
                .get("properties").get("Files").get("files").get(2).get("external").get("url").asText()));
        externalSourceFile.setExternalSourceFileObject(externalSourceFileObject);
        fileObjects.add(externalSourceFile);
        fileProperty.setFileObjects(fileObjects);
        pagePropertyMap.put("Files", fileProperty);

        UrlProperty urlProperty = new UrlProperty();
        urlProperty.setId("R%60gV");
        urlProperty.setType(PropertyType.URL);
        urlProperty.setUrl(new URL("https://www.google.com"));
        pagePropertyMap.put("URL", urlProperty);

        RichTextProperty textProperty = new RichTextProperty();
        textProperty.setId("Wo%5Dg");
        textProperty.setType(PropertyType.RICH_TEXT);
        List<RichText> texts = RichTextHelper.createDefaultArrayText("This is an apple.");
        textProperty.setTexts(texts);
        pagePropertyMap.put("String", textProperty);

        PhoneNumberProperty phoneNumberProperty = new PhoneNumberProperty();
        phoneNumberProperty.setId("%5EF_W");
        phoneNumberProperty.setType(PropertyType.PHONE_NUMBER);
        phoneNumberProperty.setPhoneNumber("0912345678");
        pagePropertyMap.put("Phone Number", phoneNumberProperty);

        RollupProperty rollupProperty = new RollupProperty();
        rollupProperty.setId("c%3A%5BL");
        rollupProperty.setType(PropertyType.ROLLUP);
        ArrayRollupProperty arrayRollupProperty = new ArrayRollupProperty();
        arrayRollupProperty.setRollupType(RollupType.ARRAY);
        textProperty = new RichTextProperty();
        textProperty.setType(PropertyType.RICH_TEXT);
        texts = RichTextHelper.createDefaultArrayText("23333");
        textProperty.setTexts(texts);
        List<PageProperty> pageProperties = Collections.singletonList(textProperty);
        arrayRollupProperty.setPageProperties(pageProperties);
        rollupProperty.setRollup(arrayRollupProperty);
        pagePropertyMap.put("Project Name", rollupProperty);

        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setId("ilF%5B");
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumber(0.12345678901234568);
        pagePropertyMap.put("Number", numberProperty);

        MultiSelectProperty multiSelectProperty = new MultiSelectProperty();
        multiSelectProperty.setId("lJ%7BX");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        List<SelectOption> selectOptions = new ArrayList<>();
        selectOption = new SelectOption();
        selectOption.setId("3e8e20dd-e36e-4863-a43e-b75b29354f42");
        selectOption.setName("Todo");
        selectOption.setColor(OptionColor.GREEN);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("b9524962-5cfc-43bc-8b64-bfff41020df4");
        selectOption.setName("Learn");
        selectOption.setColor(OptionColor.YELLOW);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("97f3b9e3-a753-4f66-b5b8-6ba68dc58b17");
        selectOption.setName("Apple");
        selectOption.setColor(OptionColor.BROWN);
        selectOptions.add(selectOption);
        multiSelectProperty.setSelectOptions(selectOptions);
        pagePropertyMap.put("Tags", multiSelectProperty);

        LastEditedByProperty lastEditedByProperty = new LastEditedByProperty();
        lastEditedByProperty.setId("nsY%3D");
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
        createdByProperty.setId("onjH");
        createdByProperty.setType(PropertyType.CREATED_BY);
        createdByProperty.setUsers(personUser);
        pagePropertyMap.put("Created By", createdByProperty);

        EmailProperty emailProperty = new EmailProperty();
        emailProperty.setId("xCs%5E");
        emailProperty.setType(PropertyType.EMAIL);
        emailProperty.setEmail("1p41p4jejo@gmail.com");
        pagePropertyMap.put("Email", emailProperty);

        CheckboxProperty checkboxProperty = new CheckboxProperty();
        checkboxProperty.setId("%7Bf%3Ec");
        checkboxProperty.setType(PropertyType.CHECKBOX);
        checkboxProperty.setCheckbox(true);
        pagePropertyMap.put("Done", checkboxProperty);

        FormulaProperty formulaProperty = new FormulaProperty();
        formulaProperty.setId("%7DgjI");
        formulaProperty.setType(PropertyType.FORMULA);
        FormulaDateObject formulaDateObject = new FormulaDateObject();
        formulaDateObject.setFormulaType(FormulaType.DATE);
        DateTime dateTime = new DateTime();
        dateTime.setDateTimeType(DateTimeType.DATE_TIME);
        dateTime.setStart(ZonedDateTime.parse("2021-09-07T09:23:00.000+00:00"));
        formulaDateObject.setDateTimeProperty(dateTime);
        formulaProperty.setFormula(formulaDateObject);
        pagePropertyMap.put("Formula", formulaProperty);

        DateProperty dateProperty = new DateProperty();
        dateProperty.setId("~Q%40z");
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

    public static Map<String, PageProperty> getPagePropertyTemplate() throws MalformedURLException {
        Map<String, PageProperty> pagePropertyMap = new LinkedHashMap<>();

        RelationProperty relationProperty = new RelationProperty();
        relationProperty.setId("%3AYwF");
        relationProperty.setType(PropertyType.RELATION);
        List<RelationObject> relationObjects = new ArrayList<>();
        RelationObject relationObject = new RelationObject();
        relationObject.setId("f34b7514-78ec-40e7-afb1-16dda0142315");
        relationObjects.add(relationObject);
        relationProperty.setRelation(relationObjects);
        pagePropertyMap.put("Related to Project (Test DB) (Property)", relationProperty);

        SelectProperty selectProperty = new SelectProperty();
        selectProperty.setId("K%5CXF");
        selectProperty.setType(PropertyType.SELECT);
        SelectOption selectOption = new SelectOption();
        selectOption.setId("94fe6b2c-c8bf-4d27-9285-3c2632d85b85");
        selectOption.setName("Doc");
        selectOption.setColor(OptionColor.GREEN);
        selectProperty.setSelectOption(selectOption);
        pagePropertyMap.put("Type", selectProperty);

        UrlProperty urlProperty = new UrlProperty();
        urlProperty.setId("R%60gV");
        urlProperty.setType(PropertyType.URL);
        urlProperty.setUrl(new URL("https://www.google.com"));
        pagePropertyMap.put("URL", urlProperty);

        RichTextProperty textProperty = new RichTextProperty();
        textProperty.setId("Wo%5Dg");
        textProperty.setType(PropertyType.RICH_TEXT);
        List<RichText> texts = RichTextHelper.createDefaultArrayText("This is an apple.");
        textProperty.setTexts(texts);
        pagePropertyMap.put("String", textProperty);

        PhoneNumberProperty phoneNumberProperty = new PhoneNumberProperty();
        phoneNumberProperty.setId("%5EF_W");
        phoneNumberProperty.setType(PropertyType.PHONE_NUMBER);
        phoneNumberProperty.setPhoneNumber("0912345678");
        pagePropertyMap.put("Phone Number", phoneNumberProperty);

        NumberProperty numberProperty = new NumberProperty();
        numberProperty.setId("ilF%5B");
        numberProperty.setType(PropertyType.NUMBER);
        numberProperty.setNumber(0.12345678901234568);
        pagePropertyMap.put("Number", numberProperty);

        MultiSelectProperty multiSelectProperty = new MultiSelectProperty();
        multiSelectProperty.setId("lJ%7BX");
        multiSelectProperty.setType(PropertyType.MULTI_SELECT);
        List<SelectOption> selectOptions = new ArrayList<>();
        selectOption = new SelectOption();
        selectOption.setId("3e8e20dd-e36e-4863-a43e-b75b29354f42");
        selectOption.setName("Todo");
        selectOption.setColor(OptionColor.GREEN);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("b9524962-5cfc-43bc-8b64-bfff41020df4");
        selectOption.setName("Learn");
        selectOption.setColor(OptionColor.YELLOW);
        selectOptions.add(selectOption);

        selectOption = new SelectOption();
        selectOption.setId("97f3b9e3-a753-4f66-b5b8-6ba68dc58b17");
        selectOption.setName("Apple");
        selectOption.setColor(OptionColor.BROWN);
        selectOptions.add(selectOption);
        multiSelectProperty.setSelectOptions(selectOptions);
        pagePropertyMap.put("Tags", multiSelectProperty);

        EmailProperty emailProperty = new EmailProperty();
        emailProperty.setId("xCs%5E");
        emailProperty.setType(PropertyType.EMAIL);
        emailProperty.setEmail("1p41p4jejo@gmail.com");
        pagePropertyMap.put("Email", emailProperty);

        CheckboxProperty checkboxProperty = new CheckboxProperty();
        checkboxProperty.setId("%7Bf%3Ec");
        checkboxProperty.setType(PropertyType.CHECKBOX);
        checkboxProperty.setCheckbox(true);
        pagePropertyMap.put("Done", checkboxProperty);

        DateProperty dateProperty = new DateProperty();
        dateProperty.setId("~Q%40z");
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

    public static Database getDatabaseTemplate(JsonNode responseJsonNode, Database responseDatabase){
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

        tw.yukina.notion.sdk.model.database.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.database.property.RelationProperty();
        relationProperty.setId("%3AYwF");
        relationProperty.setName("Related to Project (Test DB) (Property)");
        relationProperty.setType(PropertyType.RELATION);
        tw.yukina.notion.sdk.model.database.property.RelationObject relationObject = new tw.yukina.notion.sdk.model.database.property.RelationObject();
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

        tw.yukina.notion.sdk.model.database.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.database.property.SelectProperty();
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

        tw.yukina.notion.sdk.model.database.property.RollupProperty rollupProperty = new tw.yukina.notion.sdk.model.database.property.RollupProperty();
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

        tw.yukina.notion.sdk.model.database.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.database.property.NumberProperty();
        numberProperty.setId("ilF%5B");
        numberProperty.setName("Number");
        numberProperty.setType(PropertyType.NUMBER);
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(NumberFormat.NUMBER);
        numberProperty.setNumberObject(numberObject);
        propertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
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

        tw.yukina.notion.sdk.model.database.property.FormulaProperty formulaProperty = new tw.yukina.notion.sdk.model.database.property.FormulaProperty();
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

        return database;
    }

    public static Map<String, DatabaseProperty> getDatabasePropertyTemplate(JsonNode responseJsonNode) throws MalformedURLException {

        Map<String, DatabaseProperty> propertyMap = new HashMap<>();

        tw.yukina.notion.sdk.model.database.property.RelationProperty relationProperty = new tw.yukina.notion.sdk.model.database.property.RelationProperty();
        relationProperty.setId("%3AYwF");
        relationProperty.setName("Related to Project (Test DB) (Property)");
        relationProperty.setType(PropertyType.RELATION);
        tw.yukina.notion.sdk.model.database.property.RelationObject relationObject = new tw.yukina.notion.sdk.model.database.property.RelationObject();
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

        tw.yukina.notion.sdk.model.database.property.SelectProperty selectProperty = new tw.yukina.notion.sdk.model.database.property.SelectProperty();
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

        tw.yukina.notion.sdk.model.database.property.RollupProperty rollupProperty = new tw.yukina.notion.sdk.model.database.property.RollupProperty();
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

        tw.yukina.notion.sdk.model.database.property.NumberProperty numberProperty = new tw.yukina.notion.sdk.model.database.property.NumberProperty();
        numberProperty.setId("ilF%5B");
        numberProperty.setName("Number");
        numberProperty.setType(PropertyType.NUMBER);
        NumberObject numberObject = new NumberObject();
        numberObject.setNumberFormat(NumberFormat.NUMBER);
        numberProperty.setNumberObject(numberObject);
        propertyMap.put("Number", numberProperty);

        tw.yukina.notion.sdk.model.database.property.MultiSelectProperty multiSelectProperty = new tw.yukina.notion.sdk.model.database.property.MultiSelectProperty();
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

        tw.yukina.notion.sdk.model.database.property.FormulaProperty formulaProperty = new tw.yukina.notion.sdk.model.database.property.FormulaProperty();
        formulaProperty.setId("%7DgjI");
        formulaProperty.setName("Formula");
        formulaProperty.setType(PropertyType.FORMULA);
        tw.yukina.notion.sdk.model.database.property.FormulaObject formulaObject = new FormulaObject();
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

        return propertyMap;

    }
}