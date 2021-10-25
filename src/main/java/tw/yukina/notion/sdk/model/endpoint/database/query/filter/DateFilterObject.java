package tw.yukina.notion.sdk.model.endpoint.database.query.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private ZonedDateTime equalDate;

    @JsonProperty(BEFORE_FIELD)
    private ZonedDateTime beforeDate;

    @JsonProperty(AFTER_FIELD)
    private ZonedDateTime afterDate;

    @JsonProperty(ON_OR_BEFORE_FIELD)
    private ZonedDateTime onOrBeforeDate;

    @JsonProperty(IS_EMPTY_FIELD)
    private Boolean isEmpty;

    @JsonProperty(IS_NOT_EMPTY_FIELD)
    private Boolean isNotEmpty;

    @JsonProperty(ON_OR_AFTER_FIELD)
    private ZonedDateTime onOrAfterDate;

    @JsonProperty(PAST_WEEK_FIELD)
    private Boolean enablePastWeekFilter;

    @JsonProperty(PAST_MONTH_FIELD)
    private Boolean enablePastMonthFilter;

    @JsonProperty(PAST_YEAR_FIELD)
    private Boolean enablePastYearFilter;

    @JsonProperty(NEXT_WEEK_FIELD)
    private Boolean enableNextWeekFilter;

    @JsonProperty(NEXT_MONTH_FIELD)
    private Boolean enableNextMonthFilter;

    @JsonProperty(NEXT_YEAR_FIELD)
    private Boolean enableNextYearFilter;

}
