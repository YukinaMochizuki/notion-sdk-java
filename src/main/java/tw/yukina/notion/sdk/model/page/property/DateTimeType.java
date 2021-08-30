package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DateTimeType {
    DATE("date"),
    DATE_TIME("dateTime"),
    DATE_INCLUDE_END("dateIncludeEnd"),
    DATE_TIME_INCLUDE_END("dateTimeIncludeEnd");

    private final String field;

    DateTimeType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}
