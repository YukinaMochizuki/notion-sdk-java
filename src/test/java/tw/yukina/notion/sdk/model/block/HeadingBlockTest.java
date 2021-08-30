package tw.yukina.notion.sdk.model.block;

import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.Color;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.block.heading.HeadingBlockHelper;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.common.rich.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingBlockTest extends ModelTest {
    @Test
    void headingOneTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/9bda6b90679641eb973e1a1361546e4d");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        List<RichText> texts = new ArrayList<>();
        texts.add(RichTextHelper.createDefaultText("Heading 1 "));

        RichText richText = RichTextHelper.createDefaultText("One");
        richText.getAnnotations().setUnderline(true);
        richText.getAnnotations().setColor(Color.PINK);
        texts.add(richText);

        Block headingOneBlock = HeadingBlockHelper.createDefaultHeadingOne(texts);
        headingOneBlock.setId("9bda6b90-6796-41eb-973e-1a1361546e4d");

        assertEquals(block, headingOneBlock);
        response.close();
    }

    @Test
    void headingTwoTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/cf33ab7ed8b541b4a516e0578c69b97d");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading ");

        Text text = RichTextHelper.createDefaultText("2 ");
        RichTextHelper.setLinkToText(text, new URL("http://www.google.com"));
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText("Two ");
        text1.getAnnotations().setItalic(true);
        text1.getAnnotations().setColor(Color.ORANGE);
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createDefaultText("code");
        text2.getAnnotations().setCode(true);
        text2.getAnnotations().setColor(Color.GRAY);
        richTexts.add(text2);

        Block headingTwoBlock = HeadingBlockHelper.createDefaultHeadingTwo(richTexts);
        headingTwoBlock.setId("cf33ab7e-d8b5-41b4-a516-e0578c69b97d");

        assertEquals(block, headingTwoBlock);
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void headingThreeTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/d8f0c658c822454bbcfddc5fa1ff79c0");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        List<RichText> richTexts = RichTextHelper.createDefaultArrayText("Heading 3 ");

        RichText text = RichTextHelper.createDefaultText("Three");
        text.getAnnotations().setStrikethrough(true);
        richTexts.add(text);

        RichText text1 = RichTextHelper.createDefaultText(" ");
        richTexts.add(text1);

        RichText text2 = RichTextHelper.createEquationText("E=mc^2");
        richTexts.add(text2);

        Block headingThreeBlock = HeadingBlockHelper.createDefaultHeadingThree(richTexts);
        headingThreeBlock.setId("d8f0c658-c822-454b-bcfd-dc5fa1ff79c0");

        assertEquals(block, headingThreeBlock);
    }
}
