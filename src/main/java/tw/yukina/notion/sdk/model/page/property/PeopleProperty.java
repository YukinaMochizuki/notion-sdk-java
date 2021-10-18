package tw.yukina.notion.sdk.model.page.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.PropertyType;
import tw.yukina.notion.sdk.model.common.user.User;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class PeopleProperty extends PageProperty {

    private static final String PEOPLE_FIELD = "people";

    @JsonProperty(PEOPLE_FIELD)
    private List<User> users;

    @NotNull
    public static PeopleProperty of(User[] users){
        List<User> userList = Arrays.asList(users);
        PeopleProperty peopleProperty = new PeopleProperty();
        peopleProperty.setType(PropertyType.PEOPLE);
        peopleProperty.setUsers(userList);
        return peopleProperty;
    }
}
