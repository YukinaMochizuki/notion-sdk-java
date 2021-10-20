package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionRateLimitedException extends NotionAPIException {
    public NotionRateLimitedException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionRateLimitedException(String message) {
        super(message);
    }
}
