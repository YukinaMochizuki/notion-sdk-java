package tw.yukina.notion.sdk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotionClient {

    private final ApiClient apiClient;

    public NotionClient(String token){
        apiClient = new ApiClient(token);
    }
}
