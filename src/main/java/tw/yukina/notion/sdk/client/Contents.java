package tw.yukina.notion.sdk.client;

import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.block.BlockModel;

import java.util.*;

public class Contents implements Entity<Contents>, Iterable<Content> {

    private final Notion notion;

    @Getter(AccessLevel.PACKAGE)
    private final List<Content> contents;

    @Getter
    private final String parentUuid;

    @Getter(AccessLevel.PACKAGE)
    private String nextCursor;

    public Contents(Notion notion, List<Content> contents, String parentUuid, String nextCursor) {
        this.notion = notion;
        this.parentUuid = parentUuid;
        this.contents = contents;
        this.nextCursor = nextCursor;

        assert contents != null;
    }

    @Override
    public Boolean isDirty() {
        for (Content content : contents) {
            if (content.isDirty()) return true;
        }
        return false;
    }

    @Override
    public Contents refetch() {
        return notion.getContentsByUuid(parentUuid);
    }

    @Override
    public Contents save() {
        return this.notion.save(this);
    }

    @Override
    public void remove() {
        this.notion.remove(this);
        this.contents.clear();
    }

    @Override
    public Contents restore() {
        return this.notion.restore(this);
    }

    public int size() {
        fetchAll();
        return contents.size();
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    public boolean contains(Object o) {
        if (o instanceof Content) {
            Optional<Content> result = contents.stream()
                    .filter(content -> content.getBlockModel().getId()
                            .equals(((Content) o).getBlockModel().getId())).findFirst();

            return result.isPresent();
        } else {
            return false;
        }
    }

    @NotNull
    @Override
    public Iterator<Content> iterator() {
        return new Itr();
    }

    public Contents add(BlockModel blockModel) {
        this.notion.append(parentUuid, blockModel);
        return refetch();
    }

    public Contents addAll(@NotNull List<? extends BlockModel> blockModels) {
        this.notion.append(parentUuid, blockModels);
        return refetch();
    }

    public Content get(int index) {
        return contents.get(index);
    }

    public Contents remove(int index) {
        Content content = contents.get(index);
        content.remove();
        return refetch();
    }

    public int indexOf(BlockModel blockModel) {
        return contents.stream().filter(content -> content.getBlockModel().getId().equals(blockModel.getId()))
                .findFirst().map(contents::indexOf).orElse(-1);
    }

    public int indexOf(String blockId) {
        return contents.stream().filter(content -> content.getBlockModel().getId().equals(blockId))
                .findFirst().map(contents::indexOf).orElse(-1);
    }

    public void fetchAll() {
        while (nextCursor != null) {
            fetchNextPage();
        }
    }

    public void fetchNextPage() {
        if (nextCursor != null) {
            Contents nextPage = notion.getContentsByUuid(parentUuid, nextCursor);
            contents.addAll(nextPage.getContents());
            nextCursor = nextPage.getNextCursor();
        }
    }

    private class Itr implements Iterator<Content> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            if (cursor < contents.size()) {
                return true;
            } else {
                if (nextCursor != null) {
                    fetchNextPage();
                    return true;
                } else {
                    return false;
                }
            }
        }

        @Override
        public Content next() {
            return contents.get(cursor++);
        }
    }
}
