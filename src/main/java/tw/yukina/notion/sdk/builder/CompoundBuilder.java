package tw.yukina.notion.sdk.builder;

import lombok.Getter;
import tw.yukina.notion.sdk.model.endpoint.database.query.Compound;
import tw.yukina.notion.sdk.model.endpoint.database.query.CompoundType;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.DatabasePropertyFilter;

import java.util.HashSet;
import java.util.Set;

public class CompoundBuilder {

    private CompoundType compoundType;

    @Getter
    private final Set<Compound> compounds = new HashSet<>();

    @Getter
    private final Set<DatabasePropertyFilter> databasePropertyFilters = new HashSet<>();

    public CompoundBuilder setCompoundType(CompoundType compoundType){
        this.compoundType = compoundType;
        return this;
    }

    public CompoundBuilder addFilter(DatabasePropertyFilter databasePropertyFilter){
        databasePropertyFilters.add(databasePropertyFilter);
        return this;
    }

    public CompoundBuilder addCompound(Compound compound){
        compounds.add(compound);
        return this;
    }

    public Compound build(){
        Compound compound = new Compound();
        compound.setCompoundType(this.compoundType);
        compound.setCompounds(this.compounds);
        compound.setDatabasePropertyFilters(this.databasePropertyFilters);
        return compound;
    }
}
