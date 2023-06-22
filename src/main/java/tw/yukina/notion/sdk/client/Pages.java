package tw.yukina.notion.sdk.client;

import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.client.api.support.PageModels;
import tw.yukina.notion.sdk.model.page.PageModel;

import java.util.Iterator;

public class Pages implements Iterable<Page> {

    private final Notion notion;

    private final PageModels pageModels;

    public Pages(Notion notion, PageModels pageModels) {
        this.notion = notion;
        this.pageModels = pageModels;
    }

    @NotNull
    @Override
    public Iterator<Page> iterator() {
        return new Itr(this.pageModels.iterator());
    }

    public void remove() {
        this.notion.remove(this);
    }

    private class Itr implements Iterator<Page> {

        private final Iterator<PageModel> pageModelIterator;

        public Itr(Iterator<PageModel> pageModelIterator) {
            this.pageModelIterator = pageModelIterator;
        }

        @Override
        public boolean hasNext() {
            return pageModelIterator.hasNext();
        }

        @Override
        public Page next() {
            PageModel pageModel = pageModelIterator.next();
            return notion.wrapPage(pageModel);
        }
    }
}
