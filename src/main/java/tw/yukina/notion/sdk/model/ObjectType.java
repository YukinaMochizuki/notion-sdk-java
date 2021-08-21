package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ObjectType {
    DATABASE("database"),
    PAGE("page"),
    BLOCK("block"),
    USER("user"),
    WORKSPACE("workspace");

    private final String field;

    ObjectType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}
