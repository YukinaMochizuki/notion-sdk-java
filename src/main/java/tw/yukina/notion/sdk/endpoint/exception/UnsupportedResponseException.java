package tw.yukina.notion.sdk.endpoint.exception;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnsupportedResponseException extends RuntimeException {

    private JsonNode jsonNode;

    public UnsupportedResponseException(JsonNode jsonNode){
        this.jsonNode = jsonNode;
    }
}
