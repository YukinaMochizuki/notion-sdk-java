package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.rich.MentionText;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.helper.BlockHelper;
import tw.yukina.notion.sdk.model.helper.RichTextHelper;
import tw.yukina.notion.sdk.model.common.rich.mention.UserMention;
import tw.yukina.notion.sdk.model.common.user.PersonUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

class ParagraphBlockTest extends ModelTest {

    @Test
    void plainTextTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/847b5b2f483e4c6ba7e302e3ea27f4ec");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
        paragraphBlock.setId(block.getId());
        paragraphBlock.setCreatedTime(block.getCreatedTime());
        paragraphBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(paragraphBlock);

        assertEquals(block, paragraphBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void pageMentionTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/9016ca8ec5d94a39840497767f730de1");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "3ddec0b9-7fcb-46fc-ae78-4afb2406ce90";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createPageMention("Readonly Page", uuid);
        text.setHref(new URL(href));

        Block pageMentionBlock = BlockHelper.createDefaultParagraph(text);
        pageMentionBlock.setId(block.getId());
        pageMentionBlock.setCreatedTime(block.getCreatedTime());
        pageMentionBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(pageMentionBlock);

        assertEquals(block, pageMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void personMentionTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/e996b96b6925453fa804c5742c0ca5b7");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "4640eada-22ca-47be-9093-07524a2e777b";
        String avatarUrl = "https://s3-us-west-2.amazonaws.com/public.notion-static.com/1978afb7-35bb-4111-9d2f-3c67c7d00b13/FB_IMG_1594677191662_(1).jpg";
        MentionText mentionText = RichTextHelper.createPersonMention("望月知", uuid);
        UserMention userMention = (UserMention) mentionText.getMention();
        PersonUser personUser = (PersonUser) userMention.getUser();
        personUser.setAvatar(new URL(avatarUrl));
        personUser.getPerson().setEmail("1p41p4jejo@gmail.com");

        Block userMentionBlock = BlockHelper.createDefaultParagraph(mentionText);
        userMentionBlock.setId(block.getId());
        userMentionBlock.setCreatedTime(block.getCreatedTime());
        userMentionBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(userMentionBlock);

        assertEquals(block, userMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void databaseMentionTest() throws IOException {
        Response response = getResponse(BASE_URL + "/blocks/81e0f3ecb343450685fb4190b33ac342");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "287e5fe9-37aa-4fbd-9107-3e9ebe6295a1";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createDatabaseMention("Readonly Database", uuid);
        text.setHref(new URL(href));

        Block pageMentionBlock = BlockHelper.createDefaultParagraph(text);
        pageMentionBlock.setId(block.getId());
        pageMentionBlock.setCreatedTime(block.getCreatedTime());
        pageMentionBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(pageMentionBlock);

        assertEquals(block, pageMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void dateMentionTest() throws Exception {
        Response response = getResponse(BASE_URL + "/blocks/483cd981de644f4dab0f185187e2ac0d");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        Block dateTimeMentionBlock = getDateMentionBlock(block.getId(), "2021-08-10T12:50:00.000+08:00");
        dateTimeMentionBlock.setCreatedTime(block.getCreatedTime());
        dateTimeMentionBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(dateTimeMentionBlock);

        assertEquals(block, dateTimeMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();


        response = getResponse(BASE_URL + "/blocks/2658f7f5e64c4732bb16f3e82a4893c0");
        tree = Objects.requireNonNull(response.body()).string();
        responseJsonNode = getCommonObjectMapper().readTree(tree);
        block = readValueUseCommonObjectMapper(tree, Block.class);

        Block dateMentionBlock = getDateMentionBlock(block.getId(), "2021-08-21");
        dateMentionBlock.setCreatedTime(block.getCreatedTime());
        dateMentionBlock.setLastEditedTime(block.getLastEditedTime());
        serializedJsonNode = getCommonObjectMapper().valueToTree(dateMentionBlock);

        assertEquals(block, dateMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();


        response = getResponse(BASE_URL + "/blocks/a2f7178db0c44690bdbf1452d34dc631");
        tree = Objects.requireNonNull(response.body()).string();
        responseJsonNode = getCommonObjectMapper().readTree(tree);
        block = readValueUseCommonObjectMapper(tree, Block.class);

        Block dateIncludeEndMentionBlock =
                getDateMentionBlock(block.getId(),
                        "2021-08-10", "2021-08-12");
        dateIncludeEndMentionBlock.setCreatedTime(block.getCreatedTime());
        dateIncludeEndMentionBlock.setLastEditedTime(block.getLastEditedTime());
        serializedJsonNode = getCommonObjectMapper().valueToTree(dateIncludeEndMentionBlock);

        assertEquals(block, dateIncludeEndMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();


        response = getResponse(BASE_URL + "/blocks/77b9c14bfde841fcb4d554fdecc5ee89");
        tree = Objects.requireNonNull(response.body()).string();
        responseJsonNode = getCommonObjectMapper().readTree(tree);
        block = readValueUseCommonObjectMapper(tree, Block.class);

        Block dateTimeIncludeEndMentionBlock =
                getDateMentionBlock(block.getId(),
                        "2021-08-10T00:00:00.000+08:00", "2021-08-28T00:00:00.000+08:00");
        dateTimeIncludeEndMentionBlock.setCreatedTime(block.getCreatedTime());
        dateTimeIncludeEndMentionBlock.setLastEditedTime(block.getLastEditedTime());
        serializedJsonNode = getCommonObjectMapper().valueToTree(dateTimeIncludeEndMentionBlock);

        assertEquals(block, dateTimeIncludeEndMentionBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    @Test
    void equationTest() throws IOException {
        Response response = getResponse(BASE_URL + "/blocks/c79ab7a0b02d4d779e300c0e18f5f5ac");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        RichText text = RichTextHelper.createEquationText("E=mc^2");
        Block equationBlock = BlockHelper.createDefaultParagraph(text);
        equationBlock.setId(block.getId());
        equationBlock.setCreatedTime(block.getCreatedTime());
        equationBlock.setLastEditedTime(block.getLastEditedTime());
        JsonNode serializedJsonNode = getCommonObjectMapper().valueToTree(equationBlock);

        assertEquals(block, equationBlock);
        assertEquals(responseJsonNode, serializedJsonNode);
        response.close();
    }

    Block getDateMentionBlock(String id, String start) throws Exception {
        RichText text = RichTextHelper.createDateMention(start);
        Block dateMentionBlock = BlockHelper.createDefaultParagraph(text);
        dateMentionBlock.setId(id);

        return dateMentionBlock;
    }

    Block getDateMentionBlock(String id, String start, String end) throws Exception {
        RichText text = RichTextHelper.createDateMention(start, end);
        Block dateMentionBlock = BlockHelper.createDefaultParagraph(text);
        dateMentionBlock.setId(id);

        return dateMentionBlock;
    }
}