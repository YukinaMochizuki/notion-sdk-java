package tw.yukina.notion.sdk.model.common.file;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FileType {

    FILE("file"),
    EXTERNAL("external");

    private final String field;

    FileType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }

}
