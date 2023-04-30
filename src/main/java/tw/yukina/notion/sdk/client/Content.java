package tw.yukina.notion.sdk.client;

import lombok.Getter;
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

public class Content implements Entity<Content> {

    private final NotionClient notionClient;

    @Getter
    private final BlockModel blockModel;

    public Content(NotionClient notionClient, BlockModel blockModel) {
        this.notionClient = notionClient;
        this.blockModel = blockModel;

        this.entitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this.blockModel));
    }

    @Getter
    protected String entitySnapshot;

    @Override
    public Content refetch() {
        return this.notionClient.getContentByUuid(this.blockModel.getId());
    }

    @Override
    public Content save() {
        return this.notionClient.save(this.blockModel);
    }

    @Override
    public void remove() {
        this.notionClient.remove(this.blockModel);
    }

    @Override
    public Boolean isDirty() {
        String newEntitySnapshot = String.valueOf(notionClient.getApiClient().serialize(this.blockModel));
        return !newEntitySnapshot.equals(entitySnapshot);
    }

    public BookmarkBlockModel asBookmarkBlock(){
        return (BookmarkBlockModel) blockModel;
    }

    public BreadcrumbBlockModel asBreadcrumbBlock(){
        return (BreadcrumbBlockModel) blockModel;
    }

    public BulletedListBlockModel asBulletedListBlock(){
        return (BulletedListBlockModel) blockModel;
    }

    public CalloutBlockModel asCalloutBlock(){
        return (CalloutBlockModel) blockModel;
    }

    public ChildDatabaseBlockModel asChildDatabaseBlock(){
        return (ChildDatabaseBlockModel) blockModel;
    }

    public ChildPageBlockModel asChildPageBlock(){
        return (ChildPageBlockModel) blockModel;
    }

    public ColumnBlockModel asColumnBlock(){
        return (ColumnBlockModel) blockModel;
    }

    public ColumnListBlockModel asColumnListBlock(){
        return (ColumnListBlockModel) blockModel;
    }

    public DividerBlockModel asDividerBlock(){
        return (DividerBlockModel) blockModel;
    }

    public EquationBlockModel asEquationBlock(){
        return (EquationBlockModel) blockModel;
    }

    public FileBlockModel asFileBlock(){
        return (FileBlockModel) blockModel;
    }

    public HeadingOneBlockModel asHeadingOneBlock(){
        return (HeadingOneBlockModel) blockModel;
    }

    public HeadingTwoBlockModel asHeadingTwoBlock(){
        return (HeadingTwoBlockModel) blockModel;
    }

    public HeadingThreeBlockModel asHeadingThreeBlock(){
        return (HeadingThreeBlockModel) blockModel;
    }

    public ImageBlockModel asImageBlock(){
        return (ImageBlockModel) blockModel;
    }

    public LinkPreviewBlockModel asLinkPreviewBlock(){
        return (LinkPreviewBlockModel) blockModel;
    }

    public NumberedListBlockModel asNumberedListBlock(){
        return (NumberedListBlockModel) blockModel;
    }

    public ParagraphBlockModel asParagraphBlock(){
        return (ParagraphBlockModel) blockModel;
    }

    public PDFBlockModel asPDFBlock(){
        return (PDFBlockModel) blockModel;
    }

    public QuoteBlockModel asQuoteBlock(){
        return (QuoteBlockModel) blockModel;
    }

    public SyncedBlockModel asSyncedBlock(){
        return (SyncedBlockModel) blockModel;
    }

    public TableOfContentsBlockModel asTableOfContentsBlock(){
        return (TableOfContentsBlockModel) blockModel;
    }

    public TemplateBlockModel asTemplateBlock(){
        return (TemplateBlockModel) blockModel;
    }

    public TodoBlockModel asToDoBlock(){
        return (TodoBlockModel) blockModel;
    }

    public ToggleBlockModel asToggleBlock(){
        return (ToggleBlockModel) blockModel;
    }

    public VideoBlockModel asVideoBlock(){
        return (VideoBlockModel) blockModel;
    }
}
