package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FormulaFilterObject {

    public static final String TEXT_FIELD = "text";
    public static final String CHECKBOX_FIELD = "checkbox";
    public static final String NUMBER_FIELD = "number";
    public static final String DATE_FIELD = "date";

    @JsonProperty(TEXT_FIELD)
    private TextFilterObject textFilterObject;

    @JsonProperty(CHECKBOX_FIELD)
    private CheckboxFilterObject checkboxFilterObject;

    @JsonProperty(NUMBER_FIELD)
    private NumberFilterObject numberFilterObject;

    @JsonProperty(DATE_FIELD)
    private DateFilterObject dateFilterObject;
}
