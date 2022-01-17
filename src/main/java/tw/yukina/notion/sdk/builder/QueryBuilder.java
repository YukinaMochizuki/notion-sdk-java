package tw.yukina.notion.sdk.builder;

import lombok.Getter;
import tw.yukina.notion.sdk.endpoint.database.QueryDatabase;
import tw.yukina.notion.sdk.model.endpoint.database.query.Compound;
import tw.yukina.notion.sdk.model.endpoint.database.query.CompoundType;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseSort;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.DatabasePropertyFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryBuilder {

    @Getter
    private Compound compound;

    @Getter
    private final List<DatabaseSort> sorts = new ArrayList<>();

    private String startCursor;

    private Integer pageSize;

    public QueryBuilder setFirstLayerFilters(CompoundType compoundType, DatabasePropertyFilter... databasePropertyFilters){
        Compound compound = new Compound();
        compound.setCompoundType(compoundType);
        compound.getDatabasePropertyFilters().addAll(Arrays.asList(databasePropertyFilters));
        this.compound = compound;

        return this;
    }

    public QueryBuilder setFilters(Compound compound){
        this.compound = compound;
        return this;
    }

    public QueryBuilder addSort(DatabaseSort databaseSort){
        this.sorts.add(databaseSort);
        return this;
    }

    public QueryBuilder setStartCursor(String startCursor){
        this.startCursor = startCursor;
        return this;
    }

    public QueryBuilder setPageSize(Integer pageSize){
        this.pageSize = pageSize;
        return this;
    }

    public DatabaseQuery build(){
        DatabaseQuery databaseQuery = new DatabaseQuery();
        databaseQuery.setCompound(this.compound);
        databaseQuery.setPageSize(this.pageSize);
        databaseQuery.setSorts(this.sorts);
        databaseQuery.setStartCursor(this.startCursor);

        return databaseQuery;
    }
}
