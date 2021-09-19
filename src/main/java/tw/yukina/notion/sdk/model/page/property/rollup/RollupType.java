package tw.yukina.notion.sdk.model.page.property.rollup;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  RollupType {
    NUMBER("number"),
    DATE("date"),
    ARRAY("array");

    private final String field;

    RollupType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}
