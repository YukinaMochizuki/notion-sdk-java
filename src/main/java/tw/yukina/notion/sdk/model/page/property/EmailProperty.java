package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class EmailProperty extends PageProperty {

    private static final String EMAIL_FIELD = "email";

    @JsonProperty(EMAIL_FIELD)
    private String email;

    @NotNull
    public static EmailProperty of(String email){
        EmailProperty emailProperty = new EmailProperty();
        emailProperty.setType(PropertyType.EMAIL);
        emailProperty.setEmail(email);
        return emailProperty;
    }

    public boolean isValidEmailAddress(String email) {
        String patternString = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}