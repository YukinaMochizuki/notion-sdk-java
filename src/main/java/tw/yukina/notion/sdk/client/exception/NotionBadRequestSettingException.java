package tw.yukina.notion.sdk.client.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

public class NotionBadRequestSettingException extends NotionAPIException {
    public NotionBadRequestSettingException(@NotNull ObjectNode errorObject) {
        super(errorObject);
    }

    public NotionBadRequestSettingException(String message) {
        super(message);
    }
}
