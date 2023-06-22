package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.helper.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.common.rich.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingBlockTestModel extends ModelTest {
    @Test
    void headingOneTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/24f5d147619a4dbc88348250a1f4744e");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        List<RichText> texts = new ArrayList<>();
        texts.add(RichTextHelper.createDefaultText("Heading 1 "));

        RichText richText = RichTextHelper.createDefaultText("One");
        richText.getAnnotations().setUnderline(true);
        richText.getAnnotations().setColor(TextColor.PINK);
        texts.add(richText);

        BlockModel headingOneBlock = HeadingBlockHelper.createDefaultHeadingOne(texts);
        headingOneBlock.setId(block.getId());
        headingOneBlock.setCreatedTime(block.getCreatedTime());
        headingOneBlock.setLastEditedTime(block.getLastEditedTime());
        headingOneBlock.setParent(block.getParent());
        headingOneBlock.setCreatedBy(block.getCreatedBy());
        headingOneBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingOneBlock);

        System.out.println(block);

        assertEquals(block, headingOneBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void headingTwoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/d8c81d20cb2941c7a4a9e658fdcdfc58");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading ");

        Text text = RichTextHelper.createDefaultText("2 ");
        RichTextHelper.setLinkToText(text, new URL("https://www.google.com"));
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText("Two ");
        text1.getAnnotations().setItalic(true);
        text1.getAnnotations().setColor(TextColor.ORANGE);
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createDefaultText("code");
        text2.getAnnotations().setCode(true);
        text2.getAnnotations().setColor(TextColor.GRAY);
        richTexts.add(text2);

        BlockModel headingTwoBlock = HeadingBlockHelper.createDefaultHeadingTwo(richTexts);
        headingTwoBlock.setId(block.getId());
        headingTwoBlock.setCreatedTime(block.getCreatedTime());
        headingTwoBlock.setLastEditedTime(block.getLastEditedTime());
        headingTwoBlock.setParent(block.getParent());
        headingTwoBlock.setCreatedBy(block.getCreatedBy());
        headingTwoBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingTwoBlock);

        assertEquals(block, headingTwoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void headingThreeTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/7ea5d830f4354a8c814738c45dbbaa76");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        BlockModel block = readValueUseCommonObjectMapper(tree, BlockModel.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading 3 ");

        RichText text = RichTextHelper.createDefaultText("Three");
        text.getAnnotations().setStrikethrough(true);
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText(" ");
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createEquationText("E=mc^2");
        richTexts.add(text2);

        BlockModel headingThreeBlock = HeadingBlockHelper.createDefaultHeadingThree(richTexts);
        headingThreeBlock.setId(block.getId());
        headingThreeBlock.setCreatedTime(block.getCreatedTime());
        headingThreeBlock.setLastEditedTime(block.getLastEditedTime());
        headingThreeBlock.setParent(block.getParent());
        headingThreeBlock.setCreatedBy(block.getCreatedBy());
        headingThreeBlock.setLastEditedBy(block.getLastEditedBy());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingThreeBlock);

        assertEquals(block, headingThreeBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
