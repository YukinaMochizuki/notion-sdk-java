package tw.yukina.notion.sdk.endpoint.exception;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@ToString
public class NotionAPIException extends Exception {

    private int status;
    private String code;
    private String message;

    public NotionAPIException(@NotNull ObjectNode errorObject){
        status = errorObject.get("status").asInt();
        code = errorObject.get("code").asText();
        message = errorObject.get("message").asText();
    }

    public NotionAPIException(String message) {
        super(message);
    }
}
