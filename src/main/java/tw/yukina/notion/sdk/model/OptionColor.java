package tw.yukina.notion.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

    private static final List<OptionColor> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final String field;

    OptionColor(String field) {
        this.field = field;
    }

    public static OptionColor getEnum(String value) {
        if (value == null || value.length() < 1) {
            return null;
        }

        for (OptionColor t : values()) {
            if (t.name().equalsIgnoreCase(value)) {
                return t;
            }
        }

        return null;
    }

    public static OptionColor randomColor() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
