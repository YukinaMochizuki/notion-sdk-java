package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionRestrictedResourceException extends NotionAPIException {
    public NotionRestrictedResourceException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionRestrictedResourceException(String message) {
        super(message);
    }
}
