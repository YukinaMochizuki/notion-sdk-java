package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import tw.yukina.notion.sdk.model.deserializer.ZonedDateTimeDeserializer;
import tw.yukina.notion.sdk.model.serializer.ZonedDateTimeSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Properties;

public class ModelTest {

    public static final String BASE_URL = "https://api.notion.com/v1";

    @Getter
    private static OkHttpClient okHttpClient;

    @Getter
    private static SimpleModule objectMapperModule;

    @BeforeAll
    static void setUp() {
        okHttpClient = new OkHttpClient();

        objectMapperModule = new SimpleModule();
        objectMapperModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        objectMapperModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
    }

    public Request.Builder getAnotherRequestBuilder(){

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Request.Builder()
                .addHeader("Authorization", properties.getProperty("Notion.test.token2"))
                .addHeader("Notion-Version", "2021-08-16");
    }

    public Request.Builder getRequestBuilder(){

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Request.Builder()
                .addHeader("Authorization", properties.getProperty("Notion.test.token"))
                .addHeader("Notion-Version", "2021-08-16");
    }

    public Response getResponse(@NotNull String url) throws IOException {
        okhttp3.Request request = getRequestBuilder().url(url).build();
        Call call = getOkHttpClient().newCall(request);

        return call.execute();
    }

    public Response getResponse(@NotNull String url, Request.Builder builder) throws IOException {
        okhttp3.Request request = builder.url(url).build();
        Call call = getOkHttpClient().newCall(request);

        return call.execute();
    }

    public ObjectMapper getCommonObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(getObjectMapperModule());

        return objectMapper;
    }

    public <T> T readValueUseCommonObjectMapper(@NotNull Response response, @NotNull Class<T> valueType) throws IOException {
        return getCommonObjectMapper().readValue(Objects.requireNonNull(response.body()).string(), valueType);
    }

    public <T> T readValueUseCommonObjectMapper(@NotNull String tree, @NotNull Class<T> valueType) throws IOException {
        return getCommonObjectMapper().readValue(tree, valueType);
    }
}
