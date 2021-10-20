package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionObjectNotFoundException extends NotionAPIException {
    public NotionObjectNotFoundException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionObjectNotFoundException(String message) {
        super(message);
    }
}
