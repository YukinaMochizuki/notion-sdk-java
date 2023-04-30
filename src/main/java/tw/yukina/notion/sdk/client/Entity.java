package tw.yukina.notion.sdk.client;

public interface Entity<T> {
    Boolean isDirty();

    T refetch();

    T save();

    void remove();
}
