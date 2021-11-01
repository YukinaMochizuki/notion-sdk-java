package tw.yukina.notion.sdk.client;

import lombok.Getter;
import lombok.Setter;
import tw.yukina.notion.sdk.client.api.ApiClientImpl;

@Getter
@Setter
public class NotionClient {

    private final ApiClientImpl apiClient;

    public NotionClient(String token){
        apiClient = new ApiClientImpl(token);
    }
}
