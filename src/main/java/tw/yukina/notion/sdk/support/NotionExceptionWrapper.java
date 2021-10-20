package tw.yukina.notion.sdk.support;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.endpoint.exception.NotionAPIException;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class NotionExceptionWrapper {

    private Map<String, Class<? extends NotionAPIException>> exceptionDefine;

    public NotionExceptionWrapper(){
        this.exceptionDefine = new HashMap<>();
    }

    @SneakyThrows
    public NotionAPIException wrapException(@NotNull NotionAPIException notionAPIException){
        if(exceptionDefine.containsKey(notionAPIException.getCode())){
            return exceptionDefine.get(notionAPIException.getCode()).getDeclaredConstructor(ObjectNode.class)
                    .newInstance(notionAPIException.getObjectNode());
        } else {
            return notionAPIException;
        }
    }

}
