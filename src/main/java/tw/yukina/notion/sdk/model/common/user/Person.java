package tw.yukina.notion.sdk.model.common.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Person extends User {
    private static final String EMAIL_FIELD = "email";

    @JsonProperty(EMAIL_FIELD)
    private String email;
}