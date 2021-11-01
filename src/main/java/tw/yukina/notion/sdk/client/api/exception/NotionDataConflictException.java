package tw.yukina.notion.sdk.client.api.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

@ToString(callSuper = true)
public class NotionDataConflictException extends NotionAPIException {
    public NotionDataConflictException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionDataConflictException(String message) {
        super(message);
    }
}
