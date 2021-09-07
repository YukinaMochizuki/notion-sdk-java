package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.TextColor;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.heading.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.common.rich.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingBlockTest extends ModelTest {
    @Test
    void headingOneTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/9bda6b90679641eb973e1a1361546e4d");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        List<RichText> texts = new ArrayList<>();
        texts.add(RichTextHelper.createDefaultText("Heading 1 "));

        RichText richText = RichTextHelper.createDefaultText("One");
        richText.getAnnotations().setUnderline(true);
        richText.getAnnotations().setColor(TextColor.PINK);
        texts.add(richText);

        Block headingOneBlock = HeadingBlockHelper.createDefaultHeadingOne(texts);
        headingOneBlock.setId(block.getId());
        headingOneBlock.setCreatedTime(block.getCreatedTime());
        headingOneBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingOneBlock);

        assertEquals(block, headingOneBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void headingTwoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/cf33ab7ed8b541b4a516e0578c69b97d");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading ");

        Text text = RichTextHelper.createDefaultText("2 ");
        RichTextHelper.setLinkToText(text, new URL("http://www.google.com"));
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText("Two ");
        text1.getAnnotations().setItalic(true);
        text1.getAnnotations().setColor(TextColor.ORANGE);
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createDefaultText("code");
        text2.getAnnotations().setCode(true);
        text2.getAnnotations().setColor(TextColor.GRAY);
        richTexts.add(text2);

        Block headingTwoBlock = HeadingBlockHelper.createDefaultHeadingTwo(richTexts);
        headingTwoBlock.setId(block.getId());
        headingTwoBlock.setCreatedTime(block.getCreatedTime());
        headingTwoBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingTwoBlock);

        assertEquals(block, headingTwoBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void headingThreeTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/d8f0c658c822454bbcfddc5fa1ff79c0");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading 3 ");

        RichText text = RichTextHelper.createDefaultText("Three");
        text.getAnnotations().setStrikethrough(true);
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText(" ");
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createEquationText("E=mc^2");
        richTexts.add(text2);

        Block headingThreeBlock = HeadingBlockHelper.createDefaultHeadingThree(richTexts);
        headingThreeBlock.setId(block.getId());
        headingThreeBlock.setCreatedTime(block.getCreatedTime());
        headingThreeBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(headingThreeBlock);

        assertEquals(block, headingThreeBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }
}
