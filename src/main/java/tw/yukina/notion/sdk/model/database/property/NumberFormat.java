package tw.yukina.notion.sdk.model.database.property;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NumberFormat {

    NUMBER("number"),
    NUMBER_WITH_COMMAS("number_with_commas"),
    PERCENT("percent"),
    DOLLAR("dollar"),
    CANADIAN_DOLLAR("canadian_dollar"),
    EURO("euro"),
    POUND("pound"),
    YEN("yen"),
    RUBLE("ruble"),
    RUPEE("rupee"),
    WON("won"),
    YUAN("yuan"),
    REAL("real"),
    LIRA("lira"),
    RUPIAH("rupiah"),
    FRANC("franc"),
    HONG_KONG_DOLLAR("hong_kong_dollar"),
    NEW_ZEALAND_DOLLAR("new_zealand_dollar"),
    KRONA("krona"),
    NORWEGIAN_KRONE("norwegian_krone"),
    MEXICAN_PESO("mexican_peso"),
    RAND("rand"),
    NEW_TAIWAN_DOLLAR("new_taiwan_dollar"),
    DANISH_KRONE("danish_krone"),
    ZLOTY("zloty"),
    BAHT("baht"),
    FORINT("forint"),
    KORUNA("koruna"),
    SHEKEL("shekel"),
    CHILEAN_PESO("chilean_peso"),
    PHILIPPINE_PESO("philippine_peso"),
    DIRHAM("dirham"),
    COLOMBIAN_PESO("colombian_peso"),
    RIYAL("riyal"),
    RINGGIT("ringgit"),
    LEU("leu");

    private final String field;

    NumberFormat(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }
}
