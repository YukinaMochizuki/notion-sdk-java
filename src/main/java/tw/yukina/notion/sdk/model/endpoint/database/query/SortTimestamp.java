package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortTimestamp {

    CREATED_TIME("created_time"),
    LAST_EDITED_TIME("last_edited_time");

    private final String field;

    SortTimestamp(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
