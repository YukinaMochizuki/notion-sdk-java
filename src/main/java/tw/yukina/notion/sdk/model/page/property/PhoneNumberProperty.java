package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PhoneNumberProperty extends PageProperty {

    private static final String PHONE_NUMBER_FIELD = "phone_number";

    @JsonProperty(PHONE_NUMBER_FIELD)
    private String phoneNumber;

    @NotNull
    public static PhoneNumberProperty of(String phoneNumber) {
        PhoneNumberProperty phoneNumberProperty = new PhoneNumberProperty();
        phoneNumberProperty.setType(PropertyType.PHONE_NUMBER);
        phoneNumberProperty.setPhoneNumber(phoneNumber);
        return phoneNumberProperty;
    }
}
