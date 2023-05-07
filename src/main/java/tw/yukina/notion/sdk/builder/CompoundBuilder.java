package tw.yukina.notion.sdk.builder;

import lombok.Getter;
import tw.yukina.notion.sdk.model.endpoint.database.query.Compound;
import tw.yukina.notion.sdk.model.endpoint.database.query.CompoundType;
import tw.yukina.notion.sdk.model.endpoint.database.query.DatabaseQuery;
import tw.yukina.notion.sdk.model.endpoint.database.query.filter.*;

import java.util.HashSet;
import java.util.Set;

public class CompoundBuilder {

    private CompoundType compoundType = CompoundType.AND;

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

    public static CompoundBuilder builder(){
        return new CompoundBuilder();
    }

    public Compound build(){
        Compound compound = new Compound();
        compound.setCompoundType(this.compoundType);
        compound.setCompounds(this.compounds);
        compound.setDatabasePropertyFilters(this.databasePropertyFilters);
        return compound;
    }

    public DatabaseQuery buildToQuery(){
        Compound compound = build();
        DatabaseQuery databaseQuery = new DatabaseQuery();
        databaseQuery.setCompound(compound);
        return databaseQuery;
    }

    public CompoundBuilder addTitleFilterEquals(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.equalsOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterDoesNotEqual(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.doesNotEqualOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterContains(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.containsOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterDoesNotContain(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.doesNotContainOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterStartsWith(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.startsWithOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterEndsWith(String title){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.endsWithOf(title)));
        return this;
    }

    public CompoundBuilder addTitleFilterIsEmpty(boolean isEmpty){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.isEmptyOf(isEmpty)));
        return this;
    }

    public CompoundBuilder addTitleFilterIsNotEmpty(boolean isNotEmpty){
        databasePropertyFilters.add(TitlePropertyFilter.of(TextFilterObject.isNotEmptyOf(isNotEmpty)));
        return this;
    }

    public CompoundBuilder addTextFilterEquals(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.equalsOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterDoesNotEqual(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.doesNotEqualOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterContains(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.containsOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterDoesNotContain(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.doesNotContainOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterStartsWith(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.startsWithOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterEndsWith(String property, String filter){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.endsWithOf(filter)));
        return this;
    }

    public CompoundBuilder addTextFilterIsEmpty(String property, boolean isEmpty){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.isEmptyOf(isEmpty)));
        return this;
    }

    public CompoundBuilder addTextFilterIsNotEmpty(String property, boolean isNotEmpty){
        databasePropertyFilters.add(RichTextPropertyFilter.of(property, TextFilterObject.isNotEmptyOf(isNotEmpty)));
        return this;
    }

    public CompoundBuilder addSelectFilterEquals(String property, String filter){
        databasePropertyFilters.add(SelectPropertyFilter.of(property, SelectFilterObject.equalsOf(filter)));
        return this;
    }

    public CompoundBuilder addSelectFilterDoesNotEqual(String property, String filter){
        databasePropertyFilters.add(SelectPropertyFilter.of(property, SelectFilterObject.doesNotEqualOf(filter)));
        return this;
    }

    public CompoundBuilder addSelectFilterIsEmpty(String property, boolean isEmpty){
        databasePropertyFilters.add(SelectPropertyFilter.of(property, SelectFilterObject.isEmptyOf(isEmpty)));
        return this;
    }

    public CompoundBuilder addSelectFilterIsNotEmpty(String property, boolean isNotEmpty){
        databasePropertyFilters.add(SelectPropertyFilter.of(property, SelectFilterObject.isNotEmptyOf(isNotEmpty)));
        return this;
    }

    public CompoundBuilder addMultiSelectFilterEquals(String property, String filter){
        databasePropertyFilters.add(MultiSelectPropertyFilter.of(property, MultiSelectFilterObject.containsOf(filter)));
        return this;
    }

    public CompoundBuilder addMultiSelectFilterDoesNotEqual(String property, String filter){
        databasePropertyFilters.add(MultiSelectPropertyFilter.of(property, MultiSelectFilterObject.doesNotContainsOf(filter)));
        return this;
    }

    public CompoundBuilder addMultiSelectFilterIsEmpty(String property, boolean isEmpty){
        databasePropertyFilters.add(MultiSelectPropertyFilter.of(property, MultiSelectFilterObject.isEmptyOf(isEmpty)));
        return this;
    }

    public CompoundBuilder addMultiSelectFilterIsNotEmpty(String property, boolean isNotEmpty){
        databasePropertyFilters.add(MultiSelectPropertyFilter.of(property, MultiSelectFilterObject.isNotEmptyOf(isNotEmpty)));
        return this;
    }
}
