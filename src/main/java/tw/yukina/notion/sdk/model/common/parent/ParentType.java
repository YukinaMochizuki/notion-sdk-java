package tw.yukina.notion.sdk.model.common.parent;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ParentType {
    PAGE("page_id"),
    WORKSPACE("workspace"),
    DATABASE("database_id"),
    BLOCK("block_id");

    private final String field;

    ParentType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
