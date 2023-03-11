package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RelationPropertyType {

    SINGLE_PROPERTY("single_property"),
    DUAL_PROPERTY("dual_property");

    private final String field;

    RelationPropertyType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }
}
