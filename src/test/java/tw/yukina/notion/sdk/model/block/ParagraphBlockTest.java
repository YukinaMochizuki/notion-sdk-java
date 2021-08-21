package tw.yukina.notion.sdk.model.block;

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

class ParagraphBlockTest extends ModelTest {

    @Test
    void plainTextTest() {
        try {
            Response response = getResponse( BASE_URL + "/blocks/65d859d0c84d406398b902060a5b68d5");
            Block block = readValueUseCommonObjectMapper(response, Block.class);

            ParagraphBlock paragraphBlock = BlockHelper.createDefaultParagraph("Paragraph Block");
            paragraphBlock.setId("65d859d0-c84d-4063-98b9-02060a5b68d5");

            assertEquals(block, paragraphBlock);
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void pageMentionTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/a8eadea3f7f34a9d9425431cec8ff118");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        String uuid = "8d0f791d-c0fc-4ee6-bc7f-27fe0a623cad";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createPageMention("Notion Java SDK (ReadOnly)", uuid);
        text.setHref(new URL(href));

        Block pageMentionBlock = BlockHelper.createDefaultParagraph(text);
        pageMentionBlock.setId("a8eadea3-f7f3-4a9d-9425-431cec8ff118");

        assertEquals(block, pageMentionBlock);
        response.close();
    }

    @Test
    @SuppressWarnings("SpellCheckingInspection")
    void personMentionTest() throws IOException {
        Response response = getResponse( BASE_URL + "/blocks/302cd1a8c9d3413c875a167fed0cdbb0");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        String uuid = "4640eada-22ca-47be-9093-07524a2e777b";
        String avatarUrl = "https://s3-us-west-2.amazonaws.com/public.notion-static.com/1978afb7-35bb-4111-9d2f-3c67c7d00b13/FB_IMG_1594677191662_(1).jpg";
        MentionText mentionText = RichTextHelper.createPersonMention("知望月", uuid);
        UserMention userMention = (UserMention) mentionText.getMention();
        PersonUser personUser = (PersonUser) userMention.getUser();
        personUser.setAvatar(new URL(avatarUrl));
        personUser.getPerson().setEmail("1p41p4jejo@gmail.com");

        Block userMentionBlock = BlockHelper.createDefaultParagraph(mentionText);
        userMentionBlock.setId("302cd1a8-c9d3-413c-875a-167fed0cdbb0");

        assertEquals(block, userMentionBlock);
        response.close();
    }

    @Test
    void databaseMentionTest() throws IOException {
        Response response = getResponse(BASE_URL + "/blocks/59f3239743fe41d5b61e978b540721b4");
        Block block = readValueUseCommonObjectMapper(response, Block.class);

        String uuid = "8140870d-e48d-4e3d-8496-1647c7810b11";
        String href = "https://www.notion.so/" + uuid.replace("-", "");
        RichText text = RichTextHelper.createDatabaseMention("Test DB", uuid);
        text.setHref(new URL(href));

        Block pageMentionBlock = BlockHelper.createDefaultParagraph(text);
        pageMentionBlock.setId("59f32397-43fe-41d5-b61e-978b540721b4");

        assertEquals(block, pageMentionBlock);
        response.close();
    }

    @Test
    void dateMentionTest() throws Exception {
        Response response = getResponse(BASE_URL + "/blocks/265b6f6de7a14ae69fb200b3cc0266ca");
        Block block = readValueUseCommonObjectMapper(response, Block.class);
        Block dateTimeMentionBlock = getDateMentionBlock("265b6f6d-e7a1-4ae6-9fb2-00b3cc0266ca", "2021-08-10T12:50:00.000+08:00");
        assertEquals(block, dateTimeMentionBlock);

        response = getResponse(BASE_URL + "/blocks/d4c872078afe4c7fa0f8a2a5990f872f");
        block = readValueUseCommonObjectMapper(response, Block.class);
        Block dateMentionBlock = getDateMentionBlock("d4c87207-8afe-4c7f-a0f8-a2a5990f872f", "2021-08-21");
        assertEquals(block, dateMentionBlock);

        response = getResponse(BASE_URL + "/blocks/4c41de3c60ba4b688af5b97fc204e92f");
        block = readValueUseCommonObjectMapper(response, Block.class);
        Block dateIncludeEndMentionBlock =
                getDateMentionBlock("4c41de3c-60ba-4b68-8af5-b97fc204e92f",
                        "2021-08-10", "2021-08-12");
        assertEquals(block, dateIncludeEndMentionBlock);

        response = getResponse(BASE_URL + "/blocks/32e5453b489d4a05bb21c3d6aa4b41a3");
        block = readValueUseCommonObjectMapper(response, Block.class);
        Block dateTimeIncludeEndMentionBlock =
                getDateMentionBlock("32e5453b-489d-4a05-bb21-c3d6aa4b41a3",
                        "2021-08-10T00:00:00.000+08:00", "2021-08-28T00:00:00.000+08:00");
        assertEquals(block, dateTimeIncludeEndMentionBlock);
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