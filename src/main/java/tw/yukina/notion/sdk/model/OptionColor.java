package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OptionColor {
    DEFAULT("default"),
    GRAY("gray"),
    BROWN("brown"),
    ORANGE("orange"),
    YELLOW("yellow"),
    GREEN("green"),
    BLUE("blue"),
    PURPLE("purple"),
    PINK("pink"),
    RED("red");

    private final String field;

    OptionColor(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}
