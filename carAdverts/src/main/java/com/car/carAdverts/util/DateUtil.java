package com.car.carAdverts.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;

public class DateUtil {

    public static Instant stringToInstant(String date) {
        if(isNull(date)) {
            return null;
        }

        LocalDate localDate = LocalDate.parse(date);
        return localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();
    }

    public static String instantToString(Instant date) {
        if(isNull(date)) {
            return null;
        }
        final String STATISTICS_DATE_TIME_NOW_FORMAT = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STATISTICS_DATE_TIME_NOW_FORMAT)
                .withZone(ZoneId.systemDefault());

        return formatter.format(date);
    }
}
