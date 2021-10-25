package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

@ToString(callSuper = true)
public class NotionUnavailableException extends NotionAPIException {
    public NotionUnavailableException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionUnavailableException(String message) {
        super(message);
    }
}
