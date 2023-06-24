package tw.yukina.notion.sdk.model.common.user;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    PERSON("person"),
    BOT("bot");

    private final String field;

    UserType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
