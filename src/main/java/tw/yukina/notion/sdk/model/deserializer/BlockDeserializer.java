package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.block.*;
import tw.yukina.notion.sdk.model.block.file.FileBlockModel;
import tw.yukina.notion.sdk.model.block.file.ImageBlockModel;
import tw.yukina.notion.sdk.model.block.file.PDFBlockModel;
import tw.yukina.notion.sdk.model.block.file.VideoBlockModel;
import tw.yukina.notion.sdk.model.block.heading.HeadingOneBlockModel;
import tw.yukina.notion.sdk.model.block.heading.HeadingThreeBlockModel;
import tw.yukina.notion.sdk.model.block.heading.HeadingTwoBlockModel;
import tw.yukina.notion.sdk.model.block.list.BulletedListBlockModel;
import tw.yukina.notion.sdk.model.block.list.NumberedListBlockModel;
import tw.yukina.notion.sdk.model.block.list.ToggleBlockModel;

import java.io.IOException;

public class BlockDeserializer extends AbstractDeserializer<BlockModel> {
    @Override
    public BlockModel deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        addAvailableType(BlockType.PARAGRAPH.getField(), ParagraphBlockModel.class);

        addAvailableType(BlockType.HEADING_1.getField(), HeadingOneBlockModel.class);
        addAvailableType(BlockType.HEADING_2.getField(), HeadingTwoBlockModel.class);
        addAvailableType(BlockType.HEADING_3.getField(), HeadingThreeBlockModel.class);

        addAvailableType(BlockType.BULLETED_LIST_ITEM.getField(), BulletedListBlockModel.class);
        addAvailableType(BlockType.NUMBERED_LIST_ITEM.getField(), NumberedListBlockModel.class);
        addAvailableType(BlockType.TOGGLE.getField(), ToggleBlockModel.class);
        addAvailableType(BlockType.CODE.getField(), CodeBlockModel.class);
        addAvailableType(BlockType.TO_DO.getField(), TodoBlockModel.class);

        addAvailableType(BlockType.FILE.getField(), FileBlockModel.class);
        addAvailableType(BlockType.IMAGE.getField(), ImageBlockModel.class);
        addAvailableType(BlockType.PDF.getField(), PDFBlockModel.class);
        addAvailableType(BlockType.BOOKMARK.getField(), BookmarkBlockModel.class);
        addAvailableType(BlockType.VIDEO.getField(), VideoBlockModel.class);

        addAvailableType(BlockType.CHILD_PAGE.getField(), ChildPageBlockModel.class);
        addAvailableType(BlockType.CHILD_DATABASE.getField(), ChildDatabaseBlockModel.class);

        addAvailableType(BlockType.CALLOUT.getField(), CalloutBlockModel.class);
        addAvailableType(BlockType.QUOTE.getField(), QuoteBlockModel.class);
        addAvailableType(BlockType.EQUATION.getField(), EquationBlockModel.class);

        addAvailableType(BlockType.DIVIDER.getField(), DividerBlockModel.class);
        addAvailableType(BlockType.TABLE_OF_CONTENTS.getField(), TableOfContentsBlockModel.class);
        addAvailableType(BlockType.BREADCRUMB.getField(), BreadcrumbBlockModel.class);
        addAvailableType(BlockType.COLUMN.getField(), ColumnBlockModel.class);
        addAvailableType(BlockType.COLUMN_LIST.getField(), ColumnListBlockModel.class);
        addAvailableType(BlockType.LINK_PREVIEW.getField(), LinkPreviewBlockModel.class);
        addAvailableType(BlockType.TEMPLATE.getField(), TemplateBlockModel.class);
        addAvailableType(BlockType.SYNCED_BLOCK.getField(), SyncedBlockModel.class);

        return typeDeserialize(type, node, jsonParser.getCodec()).orElseThrow(() ->
                throwTypeNotFound(type, jsonParser));
    }
}
