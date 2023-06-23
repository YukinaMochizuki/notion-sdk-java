package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormulaType {
    STRING("string"),
    NUMBER("number"),
    BOOLEAN("boolean"),
    DATE("date");

    private final String field;

    FormulaType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }

}
