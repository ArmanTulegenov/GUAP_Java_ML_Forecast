package ru.guap.provider.yahoo;

import java.util.EnumMap;
import java.util.Map;

public enum Interval {

    ONE_MINUTE("1m"), TWO_MINUTES("2m"), FIVE_MINUTES("5m"), FIFTEEN_MINUTES("15m"),
    THIRTY_MINUTES("30m"), SIXTY_MINUTES("60m"), NINETY_MINUTES("90m"),
    ONE_HOUR("1h"), ONE_DAY("1d"), FIVE_DAYS("5d"), ONE_WEEK("1wk"),
    ONE_MONTH("1mo"), THREE_MONTHS("3mo");

    private static Map<Interval, String> formats = new EnumMap(Interval.class);

    static {
        formats.put(ONE_MINUTE, "yyyy-MM-dd HH:mm:ss");
        formats.put(TWO_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(FIVE_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(FIFTEEN_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(THIRTY_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(SIXTY_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(NINETY_MINUTES, "yyyy-MM-dd HH:mm:ss");
        formats.put(ONE_HOUR, "yyyy-MM-dd HH:mm:ss");
        formats.put(ONE_DAY, "yyyy-MM-dd");
        formats.put(FIVE_DAYS, "yyyy-MM-dd HH:mm:ss");
        formats.put(ONE_WEEK, "yyyy-MM-dd HH:mm:ss");
        formats.put(ONE_MONTH, "yyyy-MM-dd HH:mm:ss");
        formats.put(THREE_MONTHS, "yyyy-MM-dd HH:mm:ss");
    }
    private String value;

    Interval(String value) {
        this.value = value;
    }


}
