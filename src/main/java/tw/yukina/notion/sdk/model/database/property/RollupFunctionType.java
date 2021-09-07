package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RollupFunctionType {

    COUNT_ALL("count_all"),
    COUNT_VALUES("count_values"),
    COUNT_UNIQUE_VALUES("count_unique_values"),
    COUNT_EMPTY("count_empty"),
    COUNT_NOT_EMPTY("count_not_empty"),
    PERCENT_EMPTY("percent_empty"),
    PERCENT_NOT_EMPTY("percent_not_empty"),
    SUM("sum"),
    AVERAGE("average"),
    MEDIAN("median"),
    MIN("min"),
    MAX("max"),
    RANGE("range"),
    SHOW_ORIGINAL("show_original");

    private final String field;

    RollupFunctionType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }
}
