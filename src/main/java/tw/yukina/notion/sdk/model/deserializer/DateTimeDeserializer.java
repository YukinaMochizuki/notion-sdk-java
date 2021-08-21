package tw.yukina.notion.sdk.model.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import tw.yukina.notion.sdk.model.property.Date;
import tw.yukina.notion.sdk.model.property.DateTime;
import tw.yukina.notion.sdk.model.property.DateTimeProperty;
import tw.yukina.notion.sdk.model.property.DateTimeType;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateTimeDeserializer extends JsonDeserializer<DateTimeProperty> {

    public static Pattern datePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})\\z");

    public static Pattern dateTimePattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2})\\:(\\d{2})\\:(\\d{2})\\.(\\d{3})\\+(\\d{2})\\:(\\d{2})\\b");

    @Override
    public DateTimeProperty deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String start = node.get("start").asText();

        Optional<DateTimeProperty> optionalDateTimeProperty;

        if(node.hasNonNull("end"))optionalDateTimeProperty = parse(start, node.get("end").asText());
        else optionalDateTimeProperty = parse(start);

        return optionalDateTimeProperty
                .orElseThrow(() -> JsonMappingException.from(jsonParser, "The date " + start + " does not match any available formats"));
    }

    public static Optional<DateTimeProperty> parse(String start) {
        if (datePattern.matcher(start).find()) {
            Date date = new Date();
            date.setStart(LocalDate.parse(start));
            date.setDateTimeType(DateTimeType.DATE);
            return Optional.of(date);
        } else if(dateTimePattern.matcher(start).find()) {
            DateTime dateTime = new DateTime();
            dateTime.setStart(ZonedDateTime.parse(start));
            dateTime.setDateTimeType(DateTimeType.DATE_TIME);
            return Optional.of(dateTime);
        }

        return Optional.empty();
    }

    public static Optional<DateTimeProperty> parse(String start, String end) {
        if (datePattern.matcher(start).find()) {
            Date date = new Date();
            date.setStart(LocalDate.parse(start));
            date.setEnd(LocalDate.parse(end));
            date.setDateTimeType(DateTimeType.DATE_INCLUDE_END);
            return Optional.of(date);
        } else if(dateTimePattern.matcher(start).find()) {
            DateTime dateTime = new DateTime();
            dateTime.setStart(ZonedDateTime.parse(start));
            dateTime.setEnd(ZonedDateTime.parse(end));
            dateTime.setDateTimeType(DateTimeType.DATE_TIME_INCLUDE_END);
            return Optional.of(dateTime);
        }

        return Optional.empty();
    }
}
