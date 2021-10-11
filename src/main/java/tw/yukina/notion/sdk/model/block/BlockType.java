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
    CHILD_PAGE("child_page"),
    EMBED("embed"),
    IMAGE("image"),
    VIDEO("video"),
    FILE("file"),
    PDF("pdf"),
    BOOKMARK("bookmark"),
    UNSUPPORTED("unsupported");

    private final String field;

    BlockType(String field) {
        this.field = field;
    }

    @JsonValue
    public String getField(){
        return field;
    }

}
