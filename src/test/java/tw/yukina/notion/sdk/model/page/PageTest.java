package tw.yukina.notion.sdk.model.page;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.OptionColor;
import tw.yukina.notion.sdk.model.common.date.Date;
import tw.yukina.notion.sdk.model.common.date.DateTime;
import tw.yukina.notion.sdk.model.common.date.DateTimeType;
import tw.yukina.notion.sdk.model.common.file.*;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.SelectOption;
import tw.yukina.notion.sdk.model.common.parent.DatabaseParent;
import tw.yukina.notion.sdk.model.common.parent.ParentType;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.common.user.Person;
import tw.yukina.notion.sdk.model.common.user.PersonUser;
import tw.yukina.notion.sdk.model.common.user.UserType;
import tw.yukina.notion.sdk.model.page.property.*;
import tw.yukina.notion.sdk.model.page.property.rollup.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageTest extends ModelTest {

    @Test
    void databasePageTest() throws IOException {
        Response response = getResponse( BASE_URL + "/pages/e7eb4611798b4bcc9d6d3838dc84156b");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Page responsePage = readValueUseCommonObjectMapper(tree, Page.class);

        Page page = new Page();
        page.setId(responsePage.getId());
        page.setArchived(false);
        page.setCreatedTime(responsePage.getCreatedTime());
        page.setLastEditedTime(responsePage.getLastEditedTime());
        page.setUrl(responsePage.getUrl());

        DatabaseParent databaseParent = new DatabaseParent();
        databaseParent.setId("31d2e694-db36-4822-98a4-7dfa29cde551");
        databaseParent.setParentType(ParentType.DATABASE);
        page.setParent(databaseParent);

        Map<String, PageProperty> pagePropertyMap = new LinkedHashMap<>();
        page.setPropertyMap(pagePropertyMap);

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

        for(int i = 0; i < 2; i++) {
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
        Person person = new Person();
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
        Date date = new Date();
        date.setDateTimeType(DateTimeType.DATE);
        date.setStart(LocalDate.parse("2021-09-24"));
        dateProperty.setDateTimeProperty(date);
        pagePropertyMap.put("DeadLine", dateProperty);

        TitleProperty titleProperty = new TitleProperty();
        titleProperty.setId("title");
        titleProperty.setType(PropertyType.TITLE);
        titleProperty.setTexts(RichTextHelper.createDefaultArrayText("Page 1（Never Edit）"));
        pagePropertyMap.put("Name", titleProperty);

        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(page);

        assertEquals(responsePage, page);
        assertEquals(responseJsonNode, serializedJsonNode);
    }
}