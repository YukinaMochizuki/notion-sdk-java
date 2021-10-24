package tw.yukina.notion.sdk.model.common.query;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DirectionEnum {

    ASCENDING("ascending"),
    DESCENDING("descending");


    private final String field;

    DirectionEnum(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}