package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PropertyType {

    TITLE("title"),
    RICH_TEXT("rich_text"),
    NUMBER("number"),
    SELECT("select"),
    MULTI_SELECT("multi_select"),
    DATE("date"),
    PEOPLE("people"),
    FILES("files"),
    CHECKBOX("checkbox"),
    URL("url"),
    EMAIL("email"),
    PHONE_NUMBER("phone_number"),
    FORMULA("formula"),
    RELATION("relation"),
    ROLLUP("rollup"),
    CREATED_TIME("created_time"),
    CREATED_BY("created_by"),
    LAST_EDITED_TIME("last_edited_time"),
    LAST_EDITED_BY("last_edited_by");

    private final String field;

    PropertyType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }
}
