package tw.yukina.notion.sdk.model.common.rich;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equation {

    private static final String EXPRESSION_FIELD = "expression";

    @JsonProperty(EXPRESSION_FIELD)
    private String expression;

}
