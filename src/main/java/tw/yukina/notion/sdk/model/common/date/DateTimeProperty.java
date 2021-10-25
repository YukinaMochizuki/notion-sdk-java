package tw.yukina.notion.sdk.model.common.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import tw.yukina.notion.sdk.model.deserializer.DateTimeDeserializer;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonDeserialize(using = DateTimeDeserializer.class)
public abstract class DateTimeProperty {

    @JsonIgnore
    private DateTimeType dateTimeType;

    public abstract String startToString();

    public abstract String endToString();
}
