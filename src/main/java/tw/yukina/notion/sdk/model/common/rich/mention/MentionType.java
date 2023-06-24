package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MentionType {
    USER("user"),
    PAGE("page"),
    DATABASE("database"),
    DATE("date");

    private final String field;

    MentionType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
