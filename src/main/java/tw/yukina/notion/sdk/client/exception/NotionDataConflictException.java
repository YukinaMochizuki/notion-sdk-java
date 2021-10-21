package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionDataConflictException extends NotionAPIException {
    public NotionDataConflictException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionDataConflictException(String message) {
        super(message);
    }
}
