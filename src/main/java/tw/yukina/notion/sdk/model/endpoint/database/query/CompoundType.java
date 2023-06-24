package tw.yukina.notion.sdk.model.endpoint.database.query;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CompoundType {
    OR("or"),
    AND("and");

    private final String field;

    CompoundType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }

}
