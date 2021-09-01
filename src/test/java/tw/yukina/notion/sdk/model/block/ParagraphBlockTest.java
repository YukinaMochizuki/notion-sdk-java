package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import tw.yukina.notion.sdk.model.ModelTest;
import tw.yukina.notion.sdk.model.common.rich.MentionText;
import tw.yukina.notion.sdk.model.common.rich.RichText;
import tw.yukina.notion.sdk.model.common.rich.RichTextHelper;
import tw.yukina.notion.sdk.model.common.rich.mention.UserMention;
import tw.yukina.notion.sdk.model.common.user.PersonUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

class ParagraphBlockTest extends ModelTest {

    @Test
    void plainTextTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/65d859d0c84d406398b902060a5b68d5");
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
        Response response = getResponse( BASE_URL + "/blocks/a8eadea3f7f34a9d9425431cec8ff118");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "8d0f791d-c0fc-4ee6-bc7f-27fe0a623cad";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createPageMention("Notion Java SDK (ReadOnly)", uuid);
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
        Response response = getResponse( BASE_URL + "/blocks/302cd1a8c9d3413c875a167fed0cdbb0");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "4640eada-22ca-47be-9093-07524a2e777b";
        String avatarUrl = "https://s3-us-west-2.amazonaws.com/public.notion-static.com/1978afb7-35bb-4111-9d2f-3c67c7d00b13/FB_IMG_1594677191662_(1).jpg";
        MentionText mentionText = RichTextHelper.createPersonMention("知望月", uuid);
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
        Response response = getResponse(BASE_URL + "/blocks/59f3239743fe41d5b61e978b540721b4");
        String tree = Objects.requireNonNull(response.body()).string();
        JsonNode responseJsonNode = getCommonObjectMapper().readTree(tree);
        Block block = readValueUseCommonObjectMapper(tree, Block.class);

        String uuid = "8140870d-e48d-4e3d-8496-1647c7810b11";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createDatabaseMention("Test DB", uuid);
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
        Response response = getResponse(BASE_URL + "/blocks/265b6f6de7a14ae69fb200b3cc0266ca");
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


        response = getResponse(BASE_URL + "/blocks/d4c872078afe4c7fa0f8a2a5990f872f");
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


        response = getResponse(BASE_URL + "/blocks/4c41de3c60ba4b688af5b97fc204e92f");
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


        response = getResponse(BASE_URL + "/blocks/32e5453b489d4a05bb21c3d6aa4b41a3");
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
        Response response = getResponse(BASE_URL + "/blocks/e3b0f6306962422cb4b20cc88f6324a4");
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