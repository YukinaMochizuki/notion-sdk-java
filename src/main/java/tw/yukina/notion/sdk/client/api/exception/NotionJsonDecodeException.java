package tw.yukina.notion.sdk.client.api.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

@ToString(callSuper = true)
public class NotionJsonDecodeException extends NotionAPIException {
    public NotionJsonDecodeException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionJsonDecodeException(String message) {
        super(message);
    }
}
