package tw.yukina.notion.sdk.model.deserializer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeUnit<T> {
    private String type;
    private Class<? extends T> clazz;
}
