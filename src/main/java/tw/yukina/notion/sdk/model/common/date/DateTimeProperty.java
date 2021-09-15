package tw.yukina.notion.sdk.model.common.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import tw.yukina.notion.sdk.model.deserializer.DateTimeDeserializer;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = DateTimeDeserializer.class)
public abstract class DateTimeProperty {

    @JsonIgnore
    private DateTimeType dateTimeType;

    public abstract String startToString();

    @Nullable
    public abstract String endToString();
}
