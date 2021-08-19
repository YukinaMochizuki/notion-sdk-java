package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TextType {
    TEXT("text"),
    MENTION("mention"),
    EQUATION("equation");

    private final String field;

    TextType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }
}
