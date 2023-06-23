package tw.yukina.notion.sdk.model.block;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BlockType {
    PARAGRAPH("paragraph"),
    HEADING_1("heading_1"),
    HEADING_2("heading_2"),
    HEADING_3("heading_3"),
    CALLOUT("callout"),
    QUOTE("quote"),
    BULLETED_LIST_ITEM("bulleted_list_item"),
    NUMBERED_LIST_ITEM("numbered_list_item"),
    TO_DO("to_do"),
    TOGGLE("toggle"),
    CODE("code"),
    CHILD_PAGE("child_page"),
    CHILD_DATABASE("child_database"),
    EMBED("embed"),
    EQUATION("equation"),
    IMAGE("image"),
    VIDEO("video"),
    FILE("file"),
    PDF("pdf"),
    BOOKMARK("bookmark"),
    DIVIDER("divider"),
    TABLE_OF_CONTENTS("table_of_contents"),
    BREADCRUMB("breadcrumb"),
    COLUMN("column"),
    COLUMN_LIST("column_list"),
    LINK_PREVIEW("link_preview"),
    SYNCED_BLOCK("synced_block"),
    TEMPLATE("template"),
    LINK_TO_PAGE("link_to_page"),
    UNSUPPORTED("unsupported");

    private final String field;

    BlockType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField() {
        return field;
    }

}
