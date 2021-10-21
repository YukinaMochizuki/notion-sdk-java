package tw.yukina.notion.sdk.model.endpoint.search;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterValueEnum {

    PAGE("page"),
    DATABASE("database");

    private final String field;

    FilterValueEnum(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}