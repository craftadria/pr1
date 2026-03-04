package edu.uoc.ds.adt.util;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods for ISO-8601 date/time conversions.
 */
public final class DateUtils {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_DATE_TIME;

    private DateUtils() { }

    /**
     * Parses an ISO-8601 string (with optional 'Z' / offset) to
     * {@link LocalDateTime}.  Examples: {@code 2023-11-16T20:38:37.792Z},
     * {@code 2023-11-16T20:38:37}.
     */
    public static LocalDateTime parse(String raw) {
        if (raw == null || raw.isBlank())
            throw new IllegalArgumentException("Date string must not be blank");
        try {
            return ZonedDateTime.parse(raw.trim(), ISO).toLocalDateTime();
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(raw.trim(), ISO);
        }
    }

    /** Extracts the calendar year from a {@link LocalDateTime}. */
    public static int getYear(LocalDateTime dt) {
        return dt.getYear();
    }
}