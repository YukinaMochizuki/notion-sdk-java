package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionJsonDecodeException extends NotionAPIException {
    public NotionJsonDecodeException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionJsonDecodeException(String message) {
        super(message);
    }
}
