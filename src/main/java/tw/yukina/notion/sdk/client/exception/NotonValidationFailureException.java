package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotonValidationFailureException extends NotionAPIException {
    public NotonValidationFailureException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotonValidationFailureException(String message) {
        super(message);
    }
}
