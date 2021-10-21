package tw.yukina.notion.sdk.model.endpoint.database.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import tw.yukina.notion.sdk.model.serializer.LocalDateSerializer;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DateFilterObject {

    public static final String EQUALS_FIELD = "equals";
    public static final String BEFORE_FIELD = "before";
    public static final String AFTER_FIELD = "after";
    public static final String ON_OR_BEFORE_FIELD = "on_or_before";
    public static final String IS_EMPTY_FIELD = "is_empty";
    public static final String IS_NOT_EMPTY_FIELD = "is_not_empty";
    public static final String ON_OR_AFTER_FIELD = "on_or_after";
    public static final String PAST_WEEK_FIELD = "past_week";
    public static final String PAST_MONTH_FIELD = "past_month";
    public static final String PAST_YEAR_FIELD = "past_year";
    public static final String NEXT_WEEK_FIELD = "next_week";
    public static final String NEXT_MONTH_FIELD = "next_month";
    public static final String NEXT_YEAR_FIELD = "next_year";

    @JsonProperty(EQUALS_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate equalLocalDate;

    @JsonProperty(BEFORE_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate beforeLocalDate;

    @JsonProperty(AFTER_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate afterLocalDate;

    @JsonProperty(ON_OR_BEFORE_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate onOrBeforeLocalDate;

    @JsonProperty(IS_EMPTY_FIELD)
    private boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private boolean isNotEmpty;

    @JsonProperty(ON_OR_AFTER_FIELD)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate onOrAfterLocalDate;

    @JsonProperty(PAST_WEEK_FIELD)
    private boolean enablePastWeekFilter;

    @JsonProperty(PAST_MONTH_FIELD)
    private boolean enablePastMonthFilter;

    @JsonProperty(PAST_YEAR_FIELD)
    private boolean enablePastYearFilter;

    @JsonProperty(NEXT_WEEK_FIELD)
    private boolean enableNextWeekFilter;

    @JsonProperty(NEXT_MONTH_FIELD)
    private boolean enableNextMonthFilter;

    @JsonProperty(NEXT_YEAR_FIELD)
    private boolean enableNextYearFilter;

}
