package ru.guap.provider.yahoo;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum Interval {

    ONE_MINUTE(PeriodOfTime.DATE_TIME, "1m"), TWO_MINUTES(PeriodOfTime.DATE_TIME, "2m"), FIVE_MINUTES(PeriodOfTime.DATE_TIME, "5m"), FIFTEEN_MINUTES(PeriodOfTime.DATE_TIME, "15m"),
    THIRTY_MINUTES(PeriodOfTime.DATE_TIME, "30m"), SIXTY_MINUTES(PeriodOfTime.DATE_TIME, "60m"), NINETY_MINUTES(PeriodOfTime.DATE_TIME, "90m"),
    ONE_HOUR(PeriodOfTime.DATE_TIME, "1h"), ONE_DAY(PeriodOfTime.DATE, "1d"), FIVE_DAYS(PeriodOfTime.DATE_TIME, "5d"), ONE_WEEK(PeriodOfTime.DATE_TIME, "1wk"),
    ONE_MONTH(PeriodOfTime.DATE_TIME, "1mo"), THREE_MONTHS(PeriodOfTime.DATE_TIME, "3mo");

    private static Map<Interval, String> formats = new EnumMap<>(Interval.class);

    public static final String ISO_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    static {
        formats.put(ONE_MINUTE, ISO_DATE_TIME);
        formats.put(TWO_MINUTES, ISO_DATE_TIME);
        formats.put(FIVE_MINUTES, ISO_DATE_TIME);
        formats.put(FIFTEEN_MINUTES, ISO_DATE_TIME);
        formats.put(THIRTY_MINUTES, ISO_DATE_TIME);
        formats.put(SIXTY_MINUTES, ISO_DATE_TIME);
        formats.put(NINETY_MINUTES, ISO_DATE_TIME);
        formats.put(ONE_HOUR, ISO_DATE_TIME);
        formats.put(ONE_DAY, "yyyy-MM-dd");
        formats.put(FIVE_DAYS, ISO_DATE_TIME);
        formats.put(ONE_WEEK, ISO_DATE_TIME);
        formats.put(ONE_MONTH, ISO_DATE_TIME);
        formats.put(THREE_MONTHS, ISO_DATE_TIME);
    }
    private PeriodOfTime periodOfTime;
    private String value;

    Interval(PeriodOfTime periodOfTime, String value) {
        this.periodOfTime = periodOfTime;
        this.value = value;
    }

    public PeriodOfTime getPeriodOfTime() {
        return periodOfTime;
    }

    public static Map<Interval, String> getFormats() {
        return formats;
    }

    public static Optional<Interval> from(String intervalParam) {
        return Stream.of(Interval.values()).filter(interval -> intervalParam.equals(interval.value)).findAny();
    }
}
