package tw.yukina.notion.sdk.model.common.rich.mention;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import tw.yukina.notion.sdk.model.common.date.Date;
import tw.yukina.notion.sdk.model.common.date.DateTime;
import tw.yukina.notion.sdk.model.common.date.DateTimeProperty;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = JsonDeserializer.None.class)
public class DateMention extends Mention {
    private static final String DATE_FIELD = "date";

    @JsonProperty(DATE_FIELD)
    private DateTimeProperty dateTimeProperty;

    @NotNull
    public static DateMention of(LocalDate start) {
        Date date = Date.of(start);
        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(date);
        return dateMention;
    }

    @NotNull
    public static DateMention of(LocalDate start, LocalDate end) {
        Date date = Date.of(start, end);
        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(date);
        return dateMention;
    }

    @NotNull
    public static DateMention of(ZonedDateTime start) {
        DateTime dateTime = DateTime.of(start);
        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(dateTime);
        return dateMention;
    }

    @NotNull
    public static DateMention of(ZonedDateTime start, ZonedDateTime end) {
        DateTime dateTime = DateTime.of(start, end);
        DateMention dateMention = new DateMention();
        dateMention.setMentionType(MentionType.DATE);
        dateMention.setDateTimeProperty(dateTime);
        return dateMention;
    }
}
