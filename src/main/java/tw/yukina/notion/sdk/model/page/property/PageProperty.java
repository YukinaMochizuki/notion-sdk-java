package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.common.Property;
import tw.yukina.notion.sdk.model.deserializer.PagePropertyDeserializer;
import tw.yukina.notion.sdk.model.page.property.rollup.RollupProperty;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = PagePropertyDeserializer.class)
public class PageProperty extends Property {
    public CheckboxProperty asCheckboxProperty() {
        return (CheckboxProperty) this;
    }

    public CreatedByProperty asCreatedByProperty() {
        return (CreatedByProperty) this;
    }

    public CreatedTimeProperty asCreatedTimeProperty() {
        return (CreatedTimeProperty) this;
    }

    public DateProperty asDateProperty() {
        return (DateProperty) this;
    }

    public EmailProperty asEmailProperty() {
        return (EmailProperty) this;
    }

    public FileProperty asFileProperty() {
        return (FileProperty) this;
    }

    public FormulaProperty asFormulaProperty() {
        return (FormulaProperty) this;
    }

    public LastEditedByProperty asLastEditedByProperty() {
        return (LastEditedByProperty) this;
    }

    public LastEditedTimeProperty asLastEditedTimeProperty() {
        return (LastEditedTimeProperty) this;
    }

    public MultiSelectProperty asMultiSelectProperty() {
        return (MultiSelectProperty) this;
    }

    public NumberProperty asNumberProperty() {
        return (NumberProperty) this;
    }

    public PeopleProperty asPeopleProperty() {
        return (PeopleProperty) this;
    }

    public PhoneNumberProperty asPhoneNumberProperty() {
        return (PhoneNumberProperty) this;
    }

    public RelationProperty asRelationProperty() {
        return (RelationProperty) this;
    }

    public RollupProperty asRollupProperty() {
        return (RollupProperty) this;
    }

    public RichTextProperty asRichTextProperty() {
        return (RichTextProperty) this;
    }

    public SelectProperty asSelectProperty() {
        return (SelectProperty) this;
    }

    public TitleProperty asTitleProperty() {
        return (TitleProperty) this;
    }

    public UrlProperty asUrlProperty() {
        return (UrlProperty) this;
    }
}
