package tw.yukina.notion.sdk.model.common;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@EqualsAndHashCode
public class EmptyObject {

    private static EmptyObject emptyObject;

    @NotNull
    @Contract(value = " -> new", pure = true)
    public static EmptyObject of() {
        if (emptyObject == null) {
            emptyObject = new EmptyObject();
        }
        return emptyObject;
    }
}
