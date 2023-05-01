package tw.yukina.notion.sdk.client;

import lombok.Getter;
import tw.yukina.notion.sdk.client.api.ApiClient;
import tw.yukina.notion.sdk.client.api.ApiClientFactory;
import tw.yukina.notion.sdk.model.block.BlockModel;
import tw.yukina.notion.sdk.model.common.parent.BlockParent;
import tw.yukina.notion.sdk.model.common.parent.PageParent;
import tw.yukina.notion.sdk.model.database.DatabaseModel;
import tw.yukina.notion.sdk.model.endpoint.block.ResponseBlockList;
import tw.yukina.notion.sdk.model.endpoint.database.RequestCreateDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.RequestUpdateDatabase;
import tw.yukina.notion.sdk.model.endpoint.page.RequestCreatePage;
import tw.yukina.notion.sdk.model.endpoint.page.RequestUpdatePage;
import tw.yukina.notion.sdk.model.page.PageModel;

import java.util.ArrayList;
import java.util.List;

public class Notion implements NotionClient {

    @Getter
    private final ApiClient apiClient;

    public Notion(String token) {
        ApiClientFactory apiClientFactory = new ApiClientFactory();
        apiClientFactory.setToken(token);
        apiClientFactory.applyDefaultSetting();
        this.apiClient = apiClientFactory.build();
    }

    @Override
    public Page getPageByUuid(String uuid) {
        PageModel pageModel = apiClient.retrievePage(uuid);
        return wrapPage(pageModel);
    }

    @Override
    public Database getDatabaseByUuid(String uuid) {
        DatabaseModel databaseModel = apiClient.retrieveDatabase(uuid);
        return wrapDatabase(databaseModel);
    }

    @Override
    public Database save(DatabaseModel databaseModel) {
        DatabaseModel resultModel;
        if (databaseModel.getId() == null) {
            if (databaseModel.getParent() == null)
                throw new NotionClientValidationException("Parent is required when creating a new database");

            if (databaseModel.getTitle() == null)
                throw new NotionClientValidationException("Title is required when creating a new database");

            RequestCreateDatabase requestCreateDatabase = new RequestCreateDatabase();
            requestCreateDatabase.setTitle(databaseModel.getTitle());
            requestCreateDatabase.setProperties(databaseModel.getPropertyMap());
            requestCreateDatabase.setParent(databaseModel.getParent());
            resultModel = apiClient.createDatabase(requestCreateDatabase);
        } else {
            RequestUpdateDatabase requestUpdateDatabase = new RequestUpdateDatabase();
            requestUpdateDatabase.setTitle(databaseModel.getTitle());
            requestUpdateDatabase.setProperties(databaseModel.getPropertyMap());
            resultModel = apiClient.updateDatabase(databaseModel.getId(), requestUpdateDatabase);
        }

        return wrapDatabase(resultModel);
    }

    @Override
    public void remove(DatabaseModel databaseModel) {
        if (databaseModel.getId() == null)
            throw new NotionClientValidationException("Database id is required when removing a database");

        apiClient.deleteBlock(databaseModel.getId());
    }

    @Override
    public Database restoreDatabase(String uuid) {
        restoreContent(uuid);
        return getDatabaseByUuid(uuid);
    }

    @Override
    public Content getContentByUuid(String uuid) {
        BlockModel blockModel = apiClient.retrieveBlock(uuid);
        return wrapBlock(blockModel);
    }

    @Override
    public Content save(BlockModel blockModel) {
        BlockModel resultModel;
        if (blockModel.getId() == null) {
            if (blockModel.getParent() == null)
                throw new NotionClientValidationException("Parent is required when creating a new block");

            if (blockModel.getParent() instanceof BlockParent) {
                BlockParent blockParent = (BlockParent) blockModel.getParent();
                ResponseBlockList response = apiClient.appendBlockChildren(blockParent.getBlockId(), blockModel);
                resultModel = response.getBlocks().get(0);
            } else if (blockModel.getParent() instanceof PageParent) {
                PageParent pageParent = (PageParent) blockModel.getParent();
                ResponseBlockList response = apiClient.appendBlockChildren(pageParent.getPageId(), blockModel);
                resultModel = response.getBlocks().get(0);
            } else throw new NotionClientValidationException("Parent is not a page or block");
        } else {
            resultModel = apiClient.updateBlock(blockModel.getId(), blockModel);
        }

        return wrapBlock(resultModel);
    }

    @Override
    public void remove(BlockModel blockModel) {
        if (blockModel.getId() == null)
            throw new NotionClientValidationException("Block id is required when removing a block");

        apiClient.deleteBlock(blockModel.getId());
    }

    @Override
    public Content restoreContent(String uuid) {
        BlockModel blockModel = new BlockModel();
        blockModel.setId(uuid);
        blockModel.setArchived(false);
        return save(blockModel);
    }

    @Override
    public Contents getContentsByUuid(String uuid) {
        ResponseBlockList responseBlockList = apiClient.retrieveBlockChildren(uuid);
        return warpResponseBlockList(responseBlockList, uuid);
    }

    @Override
    public Contents getContentsByUuid(String uuid, String startCursor) {
        ResponseBlockList responseBlockList = apiClient.retrieveBlockChildren(uuid, startCursor);
        return warpResponseBlockList(responseBlockList, uuid);
    }

    @Override
    public Contents getContentsByUuid(String uuid, String startCursor, Integer pageSize) {
        ResponseBlockList responseBlockList = apiClient.retrieveBlockChildren(uuid, startCursor, pageSize);
        return warpResponseBlockList(responseBlockList, uuid);
    }

    @Override
    public Contents save(Contents contents) {
        if (contents.getParentUuid() == null)
            throw new NotionClientValidationException("Parent uuid is required when saving a block list");

        if (contents.isDirty()) {
            for (Content content : contents.getContents()) save(content.getBlockModel());
            return getContentsByUuid(contents.getParentUuid());
        } else {
            return contents;
        }
    }

    @Override
    public Contents append(String parentUuid, BlockModel blockModel) {
        ResponseBlockList responseBlockList = apiClient.appendBlockChildren(parentUuid, blockModel);
        return warpResponseBlockList(responseBlockList, parentUuid);
    }

    @Override
    public Contents append(String parentUuid, List<? extends BlockModel> blockModels) {
        ResponseBlockList responseBlockList = apiClient.appendBlockChildren(parentUuid, blockModels);
        return warpResponseBlockList(responseBlockList, parentUuid);
    }

    @Override
    public void remove(Contents contents) {
        for (Content content : contents.getContents()) remove(content.getBlockModel());
    }

    @Override
    public Contents restore(Contents contents) {
        for (Content content : contents.getContents()) restoreContent(content.getBlockModel().getId());
        return getContentsByUuid(contents.getParentUuid());
    }

    @Override
    public Page save(PageModel pageModel) {
        PageModel resultModel;
        if (pageModel.getId() == null) {
            if (pageModel.getParent() == null)
                throw new NotionClientValidationException("Parent is required when creating a new page");

            RequestCreatePage requestCreatePage = new RequestCreatePage();
            requestCreatePage.setParent(pageModel.getParent());
            requestCreatePage.setProperties(pageModel.getPropertyMap());
            resultModel = apiClient.createPage(requestCreatePage);
        } else {
            RequestUpdatePage requestUpdatePage = new RequestUpdatePage();
            requestUpdatePage.setProperties(pageModel.getPropertyMap());
            resultModel = apiClient.updatePage(pageModel.getId(), requestUpdatePage);
        }

        return wrapPage(resultModel);
    }

    @Override
    public void remove(PageModel pageModel) {
        if (pageModel.getId() == null)
            throw new NotionClientValidationException("Page id is required when removing a page");

        RequestUpdatePage requestUpdatePage = new RequestUpdatePage();
        requestUpdatePage.setArchived(true);
        apiClient.updatePage(pageModel.getId(), requestUpdatePage);
    }

    @Override
    public Page restorePage(String uuid) {
        RequestUpdatePage requestUpdatePage = new RequestUpdatePage();
        requestUpdatePage.setArchived(false);
        PageModel pageModel = apiClient.updatePage(uuid, requestUpdatePage);
        return wrapPage(pageModel);
    }

    public Database wrapDatabase(DatabaseModel databaseModel) {
        return new Database(this, databaseModel);
    }

    public Page wrapPage(PageModel pageModel) {
        return new Page(this, pageModel);
    }

    public Content wrapBlock(BlockModel blockModel) {
        return new Content(this, blockModel);
    }

    public Contents warpResponseBlockList(ResponseBlockList responseBlockList, String parentUuid) {
        List<Content> contents = wrapBlockList(responseBlockList.getBlocks());
        return wrapContents(contents, parentUuid, responseBlockList.getNextCursor());
    }

    public List<Content> wrapBlockList(List<BlockModel> blockModels) {
        List<Content> contents = new ArrayList<>();
        for (BlockModel blockModel : blockModels) {
            contents.add(wrapBlock(blockModel));
        }
        return contents;
    }

    public Contents wrapContents(List<Content> contents, String parentUuid, String nextCursor) {
        return new Contents(this, contents, parentUuid, nextCursor);
    }
}
