package tw.yukina.notion.sdk.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.yukina.notion.sdk.client.exception.NotionObjectNotFoundException;
import tw.yukina.notion.sdk.client.proxy.ApiClientHandler;
import tw.yukina.notion.sdk.model.deserializer.ZonedDateTimeDeserializer;
import tw.yukina.notion.sdk.model.serializer.ZonedDateTimeSerializer;
import tw.yukina.notion.sdk.support.NotionExceptionWrapper;

import java.lang.reflect.Proxy;
import java.time.ZonedDateTime;

@SuppressWarnings("UnusedReturnValue")
public class ApiClientFactory {

    private String token;

    private static final String NOTION_VERSION = "2021-08-16";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientFactory.class);

    private final SimpleModule objectMapperModule;

    private final ObjectMapper objectMapper;

    private final OkHttpClient okHttpClient;

    private final NotionExceptionWrapper notionExceptionWrapper;

    public ApiClientFactory(){
        this.objectMapperModule = new SimpleModule();
        this.objectMapper = new ObjectMapper();
        this.okHttpClient = new OkHttpClient();
        this.notionExceptionWrapper = new NotionExceptionWrapper();
    }

    public ApiClientFactory setToken(String token){
        this.token = token;
        return this;
    }

    public ApiClientFactory applyDefaultSetting(){
        addDefaultObjectMapperModule();
        addDefaultObjectMapperConfigure();
        addDefaultExceptionDefine();
        return this;
    }

    public ApiClient build(){
        LOGGER.info("Building Notion Api client in Api version " + NOTION_VERSION);
        ApiClientImpl apiClient = new ApiClientImpl(token);
        settingClientInstance(apiClient);
        return proxyApiClient(apiClient);
    }

    public ApiClient buildWithoutProxy(){
        LOGGER.info("Building Notion Api client in Api version " + NOTION_VERSION + "and without proxy");
        ApiClientImpl apiClient = new ApiClientImpl(token);
        settingClientInstance(apiClient);
        return apiClient;
    }

    private ApiClient proxyApiClient(ApiClientImpl apiClientImpl){
        ApiClientHandler apiClientHandler = new ApiClientHandler(apiClientImpl, notionExceptionWrapper);

        return  (ApiClient) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{ApiClient.class}, apiClientHandler);
    }

    public void settingClientInstance(@NotNull ApiClientImpl apiClient){
        apiClient.setNotionVersion(NOTION_VERSION);
        this.objectMapper.registerModule(objectMapperModule);

        apiClient.setObjectMapperModule(objectMapperModule);
        apiClient.setObjectMapper(objectMapper);
        apiClient.setOkHttpClient(okHttpClient);
    }

    public ApiClientFactory addDefaultObjectMapperModule(){
        addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        return this;
    }

    public ApiClientFactory addDefaultObjectMapperConfigure(){
        objectMapperConfigure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return this;
    }


    public ApiClientFactory objectMapperConfigure(@NotNull DeserializationFeature feature, boolean state){
        LOGGER.info("Changing state of " + feature.name() + " to " + state);
        objectMapper.configure(feature, state);
        return this;
    }

    public ApiClientFactory addDefaultExceptionDefine(){
        notionExceptionWrapper.getExceptionDefine().put("object_not_found", NotionObjectNotFoundException.class);

        return this;
    }

    public <T> ApiClientFactory addDeserializer(@NotNull Class<T> type, @NotNull JsonDeserializer<? extends T> deser){
        LOGGER.info("Adding "+ deser.getClass().getName() +" deserializer to handle " + type.getSimpleName() + " type");
        this.objectMapperModule.addDeserializer(type, deser);
        return this;
    }

    public <T> ApiClientFactory addSerializer(@NotNull Class<? extends T> type, @NotNull JsonSerializer<T> ser){
        LOGGER.info("Adding "+ ser.getClass().getName() +" serializer to handle " + type.getSimpleName() + " type");
        this.objectMapperModule.addSerializer(type, ser);
        return this;
    }
}
