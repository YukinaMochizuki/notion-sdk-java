package tw.yukina.notion.sdk.client.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.yukina.notion.sdk.client.api.exception.*;
import tw.yukina.notion.sdk.client.api.proxy.ApiClientHandler;
import tw.yukina.notion.sdk.model.deserializer.ZonedDateTimeDeserializer;
import tw.yukina.notion.sdk.model.serializer.ZonedDateTimeSerializer;
import tw.yukina.notion.sdk.support.NotionExceptionWrapper;

import java.lang.reflect.Proxy;
import java.time.ZonedDateTime;

@SuppressWarnings("UnusedReturnValue")
public class ApiClientFactory {

    private static final String NOTION_VERSION = "2022-06-28";
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientFactory.class);
    private final SimpleModule objectMapperModule;
    private final ObjectMapper objectMapper;
    private final OkHttpClient.Builder okHttpClientBuilder;
    private final NotionExceptionWrapper notionExceptionWrapper;
    private String token;

    public ApiClientFactory() {
        this.objectMapperModule = new SimpleModule();
        this.objectMapper = new ObjectMapper();
        this.okHttpClientBuilder = new OkHttpClient.Builder();
        this.notionExceptionWrapper = new NotionExceptionWrapper();
    }

    public ApiClientFactory setToken(String token) {
        this.token = token;
        return this;
    }

    public ApiClientFactory applyDefaultSetting() {
        addDefaultObjectMapperModule();
        addDefaultObjectMapperConfigure();
        addDefaultExceptionDefine();
        addDefaultObjectMapperSerializationInclusion();
        addLoggingInterceptor();
        return this;
    }

    public ApiClient build() {
        LOGGER.info("Building Notion Api client in Api version " + NOTION_VERSION);
        ApiClientImpl apiClient = new ApiClientImpl(token);
        settingClientInstance(apiClient);
        return proxyApiClient(apiClient);
    }

    public ApiClient buildWithoutProxy() {
        LOGGER.info("Building Notion Api client in Api version " + NOTION_VERSION + " and without proxy");
        ApiClientImpl apiClient = new ApiClientImpl(token);
        settingClientInstance(apiClient);
        return apiClient;
    }

    private ApiClient proxyApiClient(ApiClientImpl apiClientImpl) {
        ApiClientHandler apiClientHandler = new ApiClientHandler(apiClientImpl, notionExceptionWrapper);

        return (ApiClient) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{ApiClient.class}, apiClientHandler);
    }

    public void settingClientInstance(@NotNull ApiClientImpl apiClient) {
        apiClient.setNotionVersion(NOTION_VERSION);
        this.objectMapper.registerModule(objectMapperModule);

        apiClient.setObjectMapperModule(objectMapperModule);
        apiClient.setObjectMapper(objectMapper);
        apiClient.setOkHttpClient(okHttpClientBuilder.build());
    }

    public ApiClientFactory addDefaultObjectMapperModule() {
        addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());
        return this;
    }

    public ApiClientFactory addDefaultObjectMapperConfigure() {
        objectMapperConfigure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapperConfigure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return this;
    }

    public ApiClientFactory addDefaultObjectMapperSerializationInclusion() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return this;
    }

    public ApiClientFactory objectMapperConfigure(@NotNull DeserializationFeature feature, boolean state) {
        LOGGER.info("Changing ObjectMapper state of " + feature.name() + " to " + state);
        objectMapper.configure(feature, state);
        return this;
    }

    public ApiClientFactory objectMapperConfigure(@NotNull SerializationFeature feature, boolean state) {
        LOGGER.info("Changing ObjectMapper state of " + feature.name() + " to " + state);
        objectMapper.configure(feature, state);
        return this;
    }

    public ApiClientFactory addDefaultExceptionDefine() {
        notionExceptionWrapper.getExceptionDefine().put("invalid_json", NotionJsonDecodeException.class);
        notionExceptionWrapper.getExceptionDefine().put("invalid_request_url", NotionBadRequestSettingException.class);
        notionExceptionWrapper.getExceptionDefine().put("invalid_request", NotionBadRequestSettingException.class);
        notionExceptionWrapper.getExceptionDefine().put("validation_error", NotonValidationFailureException.class);
        notionExceptionWrapper.getExceptionDefine().put("missing_version", NotionBadRequestSettingException.class);
        notionExceptionWrapper.getExceptionDefine().put("unauthorized", NotionUnauthorizedException.class);
        notionExceptionWrapper.getExceptionDefine().put("restricted_resource", NotionRestrictedResourceException.class);
        notionExceptionWrapper.getExceptionDefine().put("object_not_found", NotionObjectNotFoundException.class);
        notionExceptionWrapper.getExceptionDefine().put("conflict_error", NotionDataConflictException.class);
        notionExceptionWrapper.getExceptionDefine().put("rate_limited", NotionRateLimitedException.class);
        notionExceptionWrapper.getExceptionDefine().put("internal_server_error", NotionUnavailableException.class);
        notionExceptionWrapper.getExceptionDefine().put("service_unavailable", NotionUnavailableException.class);
        notionExceptionWrapper.getExceptionDefine().put("database_connection_unavailable",
                NotionUnavailableException.class);
        return this;
    }

    public <T> ApiClientFactory addDeserializer(@NotNull Class<T> type, @NotNull JsonDeserializer<? extends T> deser) {
        LOGGER.info("Adding " + deser.getClass().getName() + " deserializer to handle "
                + type.getSimpleName() + " type");
        this.objectMapperModule.addDeserializer(type, deser);
        return this;
    }

    public <T> ApiClientFactory addSerializer(@NotNull Class<? extends T> type, @NotNull JsonSerializer<T> ser) {
        LOGGER.info("Adding " + ser.getClass().getName() + " serializer to handle " + type.getSimpleName() + " type");
        this.objectMapperModule.addSerializer(type, ser);
        return this;
    }

    public ApiClientFactory addLoggingInterceptor() {
        LOGGER.info("Adding logging interceptor to OkHttpClient");
        okHttpClientBuilder.addInterceptor(new LoggingInterceptor());
        return this;
    }
}
