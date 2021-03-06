package tw.yukina.notion.sdk.client;

import lombok.Getter;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientFactory;

public class NotionClient {

    @Getter
    private final ApiClient apiClient;

    public NotionClient(String token){
        ApiClientFactory apiClientFactory = new ApiClientFactory();
        apiClientFactory.setToken(token);
        apiClientFactory.applyDefaultSetting();
        this.apiClient = apiClientFactory.build();
    }

    public NotionSession openSession(){
        return new NotionSessionImpl(apiClient);
    }
}
